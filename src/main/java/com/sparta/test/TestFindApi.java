package com.sparta.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


@Controller
public class TestFindApi {

    @RequestMapping(value="/sample/getAddrApi.do")
    public void getAddrApi(HttpServletRequest req, ModelMap model, HttpServletResponse response) throws Exception {
        // 요청변수 설정
        String admCd = req.getParameter("admCd");
        String rnMgtSn = req.getParameter("rnMgtSn");
        String udrtYn = req.getParameter("udrtYn");
        String buldMnnm = req.getParameter("buldMnnm");
        String buldSlno = req.getParameter("buldSlno");
        String confmKey = req.getParameter("confmKey");
        String resultType = req.getParameter("resultType"); // 요청 변수 설정 (검색결과형식 설정, json)

        // OPEN API 호출 URL 정보 설정
        String apiUrl = "https://www.juso.go.kr/addrlink/addrCoordApi.do?admCd ="+admCd+"&rnMgtSn="+rnMgtSn+"&udrtYn="+udrtYn+"&buldMnnm="+buldMnnm+"&buldSlno="+buldSlno+"&confmKey="+confmKey+"&resultType="+resultType;
        URL url = new URL(apiUrl);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
        StringBuffer sb = new StringBuffer();
        String tempStr = null;

        while(true){
            tempStr = br.readLine();
            if(tempStr == null) break;
            sb.append(tempStr);								// 응답결과 JSON 저장
        }
        br.close();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");
        response.getWriter().write(sb.toString());			// 응답결과 반환
    }
}
