package service.office;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.office.OfficeDto;
import dao.office.OfficeDao;
import service.CommandProcess;

public class OfficeUpdateProAction implements CommandProcess {

	
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			String pageNum = request.getParameter("pageNum");
			// 업로드 파일 사이즈
			int maxSize = 5 * 1024 * 1024;
			// 업로드될 폴더경로
			String fileSave = "/img";
			String realPath = request.getSession().getServletContext().getRealPath(fileSave);
			System.out.println("OfficeWriteProAction realPath->" + realPath);
					
			MultipartRequest mp = new MultipartRequest(request, realPath, maxSize, "utf-8",	new DefaultFileRenamePolicy());
			OfficeDto office = new OfficeDto();
			System.out.println("office ->" + office);
			
			int ofc_code = Integer.parseInt(mp.getParameter("ofc_code"));			
			String ofc_name = mp.getParameter("ofc_name");
			int ofctype_code = Integer.parseInt(mp.getParameter("ofctype_code"));
			String ofc_location = mp.getParameter("ofc_location");
			String ofc_tel = mp.getParameter("ofc_tel");
			String ofctype_name = mp.getParameter("ofctype_name");
			String ofc_region = mp.getParameter("ofc_region");			
			System.out.println("mp.getParameter(\"picture1\") -->"+ mp.getParameter("picture1"));
			
			String picture1 = mp.getParameter("picture1"); 
			String picture2 = mp.getParameter("picture2"); 
			String picture3 = mp.getParameter("picture3"); 
			String picture4 = mp.getParameter("picture4"); 
			String picture5 = mp.getParameter("picture5"); 
			String picture6 = mp.getParameter("picture6"); 
			String picture7 = mp.getParameter("picture7"); 
			String picture8 = mp.getParameter("picture8"); 
			String picture9 = mp.getParameter("picture9"); 
			String picture10 = mp.getParameter("picture10"); 
			
			/*office.setPicture1(mp.getParameter("picture1"));
			office.setPicture2(mp.getParameter("picture2"));
			office.setPicture3(mp.getParameter("picture3"));
			office.setPicture4(mp.getParameter("picture4"));
			office.setPicture5(mp.getParameter("picture5"));
			office.setPicture6(mp.getParameter("picture6"));
			office.setPicture7(mp.getParameter("picture7"));
			office.setPicture8(mp.getParameter("picture8"));
			office.setPicture9(mp.getParameter("picture9"));
			office.setPicture10(mp.getParameter("picture10"));*/
						
			int si = 0;
			String[] strArr = new String[10];
			Enumeration en = mp.getFileNames();
			
			while (en.hasMoreElements()) {
				System.out.println("OfficeWriteProAction en.hasMoreElements() loop....");
				//  input 태그의 속성이 file인 name 속성값:패라미터이름
				String pic = (String) en.nextElement();
				System.out.println("OfficeWriteProAction en.hasMoreElements() picture1->"+pic);
       			// switch (si) case를 0부터 계산하기 때문에( 1 -> 0) 1개를 빼주고 시작
				String updateFile = mp.getFilesystemName(pic);
				si = Integer.parseInt(pic.substring(7))-1;
				System.out.println("OfficeWriteProAction en.hasMoreElements() si->"+si);
				
//				if(updateFile == null) office.setPicture1(pic);
//				else office.setPicture1(updateFile);

//				if(pic != null) office.setPicture1(pic);
	
				String picture = mp.getFilesystemName(pic); // 서버에 저장된 파일이름
				// if (picture == null)	continue;
				System.out.println("OfficeWriteProAction en.hasMoreElements() picture->"+picture);
				
				// 각각의 이미지를 String 배열에 넣는다.
				strArr[si] = picture;

				switch (si) {
				case 0:
					System.out.println("OfficeWriteProAction strArr[0]->" + strArr[si]);
					if(picture1 !=null && !picture1.equals("") && strArr[si] == null) {
						office.setPicture1(picture1);	
					}else {
						office.setPicture1(strArr[si]);	
					}
					break;
				case 1:
					System.out.println("OfficeWriteProAction strArr[1]->" + strArr[si]);
					if(picture2 !=null && !picture2.equals("") && strArr[si] == null) {
						office.setPicture2(picture2);	
					}else {
						office.setPicture2(strArr[si]);	
					}
					break;
				case 2:
					System.out.println("OfficeWriteProAction strArr[2]->" + strArr[si]);
					if(picture3 !=null && !picture3.equals("") && strArr[si] == null) {
						office.setPicture3(picture3);	
					}else {
						office.setPicture3(strArr[si]);	
					}
					break;
				case 3:
					System.out.println("OfficeWriteProAction strArr[3]->" + strArr[si]);
					if(picture4 !=null && !picture4.equals("") && strArr[si] == null) {
						office.setPicture4(picture4);	
					}else {
						office.setPicture4(strArr[si]);	
					}
					break;
				case 4:
					System.out.println("OfficeWriteProAction strArr[4]->" + strArr[si]);
					if(picture5 !=null && !picture5.equals("") && strArr[si] == null) {
						office.setPicture5(picture5);	
					}else {
						office.setPicture5(strArr[si]);	
					}
					break;
				case 5:
					System.out.println("OfficeWriteProAction strArr[5]->" + strArr[si]);
					if(picture6 !=null && !picture6.equals("") && strArr[si] == null) {
						office.setPicture6(picture6);	
					}else {
						office.setPicture6(strArr[si]);	
					}
					break;
				case 6:
					System.out.println("OfficeWriteProAction strArr[6]->" + strArr[si]);
					if(picture7 !=null && !picture7.equals("") && strArr[si] == null) {
						office.setPicture7(picture7);	
					}else {
						office.setPicture7(strArr[si]);	
					}
					break;
				case 7:
					System.out.println("OfficeWriteProAction strArr[7]->" + strArr[si]);
					if(picture8 !=null && !picture8.equals("") && strArr[si] == null) {
						office.setPicture8(picture8);	
					}else {
						office.setPicture8(strArr[si]);	
					}
					break;
				case 8:
					System.out.println("OfficeWriteProAction strArr[8]->" + strArr[si]);
					if(picture9 !=null && !picture9.equals("") && strArr[si] == null) {
						office.setPicture9(picture9);	
					}else {
						office.setPicture9(strArr[si]);	
					}
					break;
				case 9:
					System.out.println("OfficeWriteProAction strArr[9]->" + strArr[si]);
					if(picture10 !=null && !picture10.equals("") && strArr[si] == null) {
						office.setPicture10(picture10);	
					}else {
						office.setPicture10(strArr[si]);	
					}
					break;
				default:
					break;
				}
				si++; // 배열 증가

				System.out.println("OfficeWriteProAction , realPath->" + realPath);
//				System.out.println("OfficeWriteProAction , 파라메타 이름->" + picture1);
//				System.out.println("OfficeWriteProAction , 실제파일이름 ->" + existFile);
				System.out.println("OfficeWriteProAction , 파일 타입->" + updateFile);
			}

			System.out.println("OfficeWriteProAction office before....");
			office.setOfc_code(Integer.parseInt(request.getParameter("ofc_code")));
			office.setOfc_name(mp.getParameter("ofc_name"));
			office.setOfctype_code(Integer.parseInt(mp.getParameter("ofctype_code")));
			office.setOfc_location(mp.getParameter("ofc_location"));
			office.setOfc_tel(mp.getParameter("ofc_tel"));
			office.setOfctype_name(mp.getParameter("ofctype_name"));
			office.setOfc_region(mp.getParameter("ofc_region"));
			/*office.setPicture1(mp.getParameter("picture1"));
			office.setPicture2(mp.getParameter("picture2"));
			office.setPicture3(mp.getParameter("picture3"));
			office.setPicture4(mp.getParameter("picture4"));
			office.setPicture5(mp.getParameter("picture5"));
			office.setPicture6(mp.getParameter("picture6"));
			office.setPicture7(mp.getParameter("picture7"));
			office.setPicture8(mp.getParameter("picture8"));
			office.setPicture9(mp.getParameter("picture9"));
			office.setPicture10(mp.getParameter("picture10"));*/
			
			// 1 먼저 pk가 존재하는지 sql적용한다
			// 2 pk가 존재하면 업데이트
			// 3 존재하지 않으면 인서트
			
			OfficeDao od = OfficeDao.getInstance();
			int result = od.update(office);
			System.out.println("OfficeWriteProAction result ->?  " + result);

			
			request.setAttribute("result", result);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("ofc_code", office.getOfc_code());
			request.setAttribute("ofc_name", office.getOfc_name());
			request.setAttribute("ofctype_code", office.getOfctype_code());
			request.setAttribute("ofc_location", office.getOfc_location());
			request.setAttribute("ofc_tel", office.getOfc_tel());
			request.setAttribute("ofctype_name", office.getOfctype_name());
			request.setAttribute("ofc_region", office.getOfc_region());
			request.setAttribute("picture1", office.getPicture1());
			request.setAttribute("picture2", office.getPicture2());
			request.setAttribute("picture3", office.getPicture3());
			request.setAttribute("picture4", office.getPicture4());
			request.setAttribute("picture5", office.getPicture5());
			request.setAttribute("picture6", office.getPicture6());
			request.setAttribute("picture7", office.getPicture7());
			request.setAttribute("picture8", office.getPicture8());
			request.setAttribute("picture9", office.getPicture9());
			request.setAttribute("picture10", office.getPicture10());

			System.out.println("WriteProAction 종료 b_num + result ->" + office.getOfc_code() + "...." + result);
		
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return "office/officeUpdatePro.jsp";
	}

}
