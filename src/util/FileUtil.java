package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileUtil extends HttpServlet {
	public void filUpload(HttpServletRequest request, String path){
		try {
			request.setCharacterEncoding("utf-8");
			int maxSize = 5 * 1024 * 1024;  // 5 * 1K * 1K = 5M
			String fileSave = path; //ex:) c:/images/main/photo/
			//String realPath = request.getServletContext().getRealPath(fileSave);
			String realPath = fileSave;
			System.out.println("realPath->"+realPath);
			MultipartRequest mp;
			mp = new MultipartRequest(request,realPath,	maxSize,"utf-8", new DefaultFileRenamePolicy());
		
			Enumeration en = mp.getFileNames();
			while(en.hasMoreElements()) {
				//input 태그의 속성이 file인 태그의 name 속성값 :파라미터이름
				String picture1 = (String)en.nextElement(); 
				//System.out.println("1"+filename1);
				//서버에 저장된 파일 이름 
				String picture = mp.getFilesystemName(picture1); 
				//System.out.println("2"+filename);
				//전송전 원래의 파일 이름 
				String original = mp.getOriginalFileName(picture1);
				//System.out.println("3"+original);
				//전송된 파일의 내용 타입 
				String type = mp.getContentType(picture1); 
				//전송된 파일속성이 file인 태그의 name 속성값을 이용해 파일객체생성 
				File file = mp.getFile(picture1); 
				//폴더 없음 생성
				/*if (!file.exists()) {
					file.mkdirs();
				}*/
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void fileDelete(String path, String filename){
		File file = new File(path+filename);
		if( file.exists() ){ 
			if(file.delete()){ 
				System.out.println("파일삭제 성공"); 
			}else{ 
				System.out.println("파일삭제 실패"); 
			} 
		}else{ 
			System.out.println("파일이 존재하지 않습니다."); 
		}
	}
	
	public void filDownload(HttpServletRequest request,	HttpServletResponse response, String filePath, String picture) throws IOException {
		 
		request.setCharacterEncoding("UTF-8");
	    // 파일 업로드된 경로
	    //String filePath = request.getSession().getServletContext().getRealPath("/");
	    String savePath = filePath;
	 
	    InputStream in = null;
	    OutputStream os = null;
	    File file = null;
	    boolean skip = false;
	    String client = "";
	    try{
	        // 파일을 읽어 스트림에 담기
	        try{
	            file = new File(savePath, picture);
	            in = new FileInputStream(file);
	        }catch(FileNotFoundException fe){
	            skip = true;
	        }
	        client = request.getHeader("User-Agent");
	 
	        // 파일 다운로드 헤더 지정
	        response.reset() ;
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Description", "JSP Generated Data");
	 
	        if(!skip){
	            // IE
	            if(client.indexOf("MSIE") != -1){
	                response.setHeader ("Content-Disposition", "attachment; filename="+new String(picture.getBytes("KSC5601"),"ISO8859_1"));
	            }else{
	                // 한글 파일명 처리
	            	picture = new String(picture.getBytes("utf-8"),"iso-8859-1");
	 
	                response.setHeader("Content-Disposition", "attachment; filename=\"" + picture + "\"");
	                response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
	            } 
	            response.setHeader ("Content-Length", ""+file.length() );
	       
	            os = response.getOutputStream();
	            byte b[] = new byte[(int)file.length()];
	            int leng = 0;
	             
	            while( (leng = in.read(b)) > 0 ){
	                os.write(b,0,leng);
	            }
	        }else{
	        	PrintWriter out = response.getWriter();
	            response.setContentType("text/html;charset=UTF-8");
	            out.println("<script language='javascript'>alert('파일을 찾을 수 없습니다');history.back();</script>");
	        }
	         
	        in.close();
	        os.close();
	 
	    }catch(Exception e){
	      e.printStackTrace();
	    }
	}
	
}
