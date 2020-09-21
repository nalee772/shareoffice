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
import util.FileUtil;

public class OfficeWriteProAction implements CommandProcess {

	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			OfficeDto office = new OfficeDto();
			System.out.println("office ->" + office);

			int maxSize = 5 * 1024 * 1024;
			int si = 0;
			String[] strArr = new String[10];

			String fileSave = "/img";
			String realPath = request.getSession().getServletContext().getRealPath(fileSave);
			System.out.println("OfficeWriteProAction realPath->" + realPath);

			MultipartRequest mp = new MultipartRequest(request, realPath, maxSize, "utf-8",	new DefaultFileRenamePolicy());
			String pageNum = mp.getParameter("pageNum");
			Enumeration en = mp.getFileNames();
			while (en.hasMoreElements()) {
				System.out.println("OfficeWriteProAction en.hasMoreElements() loop....");
				//  input 태그의 속성이 file인 name 속성값:패라미터이름
				String picture1 = (String) en.nextElement();
				System.out.println("OfficeWriteProAction en.hasMoreElements() picture1->"+picture1);
				// switch (si) case를 0부터 계산하기 때문에( 1 -> 0) 1개를 빼주고 시작
				si = Integer.parseInt(picture1.substring(7))-1;
				System.out.println("OfficeWriteProAction en.hasMoreElements() si->"+si);
				
				String picture = mp.getFilesystemName(picture1); // 서버에 저장된 파일이름
				// String picture = mp.getFilesystemName(picture1);
				// if (picture == null)	continue;
				System.out.println("OfficeWriteProAction en.hasMoreElements() picture->"+picture);
				// 각각의 이미지를 String 배열에 넣는다.
				strArr[si] = picture;

				String original = mp.getOriginalFileName(picture1); // 전송전 원래의 파일이름
				String type = mp.getContentType(picture1);// 전송된 팡일의 내용 타입
				File file = mp.getFile(picture1); // 전송된 파일속성이 file인 태그의 name속성값을 이요해 파일 객체 생성
				
				switch (si) {
				case 0:
					System.out.println("OfficeWriteProAction strArr[0]->" + strArr[si]);
					office.setPicture1(strArr[si]);
					break;
				case 1:
					System.out.println("OfficeWriteProAction strArr[1]->" + strArr[si]);
					office.setPicture2(strArr[si]);
					break;
				case 2:
					System.out.println("OfficeWriteProAction strArr[2]->" + strArr[si]);
					office.setPicture3(strArr[si]);
					break;
				case 3:
					System.out.println("OfficeWriteProAction strArr[3]->" + strArr[si]);
					office.setPicture4(strArr[si]);
					break;
				case 4:
					System.out.println("OfficeWriteProAction strArr[4]->" + strArr[si]);
					office.setPicture5(strArr[si]);
					break;
				case 5:
					System.out.println("OfficeWriteProAction strArr[5]->" + strArr[si]);
					office.setPicture6(strArr[si]);
					break;
				case 6:
					System.out.println("OfficeWriteProAction strArr[6]->" + strArr[si]);
					office.setPicture7(strArr[si]);
					break;
				case 7:
					System.out.println("OfficeWriteProAction strArr[7]->" + strArr[si]);
					office.setPicture8(strArr[si]);
					break;
				case 8:
					System.out.println("OfficeWriteProAction strArr[8]->" + strArr[si]);
					office.setPicture9(strArr[si]);
					break;
				case 9:
					System.out.println("OfficeWriteProAction strArr[9]->" + strArr[si]);
					office.setPicture10(strArr[si]);
					break;
				default:
					break;
				}
				si++; // 배열 증가

				System.out.println("OfficeWriteProAction , realPath->" + realPath);
				System.out.println("OfficeWriteProAction , 파라메타 이름->" + picture1);
				System.out.println("OfficeWriteProAction , 실제파일이름 ->" + original);
				System.out.println("OfficeWriteProAction , 파일 타입->" + type);
			}

			System.out.println("OfficeWriteProAction office before....");

			office.setOfc_code(Integer.parseInt(mp.getParameter("ofc_code")));
			System.out.println("OfficeWriteProAction office ofc_code ->" + mp.getParameter("ofc_code"));
			office.setOfc_name(mp.getParameter("ofc_name"));
			System.out.println("OfficeWriteProAction office ofc_name ->" + mp.getParameter("ofc_name"));
			office.setOfctype_code(Integer.parseInt(mp.getParameter("ofctype_code")));
			System.out.println("OfficeWriteProAction office ofctype_code ->" + mp.getParameter("ofctype_code"));
			office.setOfc_location(mp.getParameter("ofc_location"));
			System.out.println("OfficeWriteProAction office ofc_location ->" + mp.getParameter("ofc_location"));
			office.setOfc_tel(mp.getParameter("ofc_tel"));
			System.out.println("OfficeWriteProAction office ofc_tel ->" + mp.getParameter("ofc_tel"));
			office.setOfctype_name(mp.getParameter("ofctype_name"));
			System.out.println("OfficeWriteProAction office ofctype_name ->" + mp.getParameter("ofctype_name"));
			office.setOfc_region(mp.getParameter("ofc_region"));
			System.out.println("OfficeWriteProAction office ofc_region ->" + mp.getParameter("ofc_region"));
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
			
			
			
			OfficeDao od = OfficeDao.getInstance();
			int result = od.insert(office);
			System.out.println("OfficeWriteProAction result ->?  " + result);

			request.setAttribute("office", office);
			request.setAttribute("result", result);
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
		return "office/officeWritePro.jsp";
	}

}