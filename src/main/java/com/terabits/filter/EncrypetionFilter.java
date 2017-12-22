package com.terabits.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.terabits.utils.AESUtil;


/**
 * Servlet Filter implementation class EncrypetionFilter
 */

/*
 * select fuzzy parameter
 * AES 加密后为：77758ABE68834A2EC957BF3A927F407A8DDB80F0E86F02CC7FCB40F19E2D1C7D7C52AC4DF39A91FCD43CD7777893E327B221A248F3494CABAA2B94DC4D0B4B4503283133B8D73302DCAE1D83260B1366802D57B0C56E64E2F9F7D6CC45E1D9A3C2BC065DCFFFD103CE1BCCBF62CA89801C46F4D6BEC3279AB7C05E8316176AB89C9AC5E3A95FA09AB4281CCEC68556268774268264D40BBD31F64DA3B87D03FB9C01AED99FD94FE1240C4F982344F8C07C52AC4DF39A91FCD43CD7777893E327B221A248F3494CABAA2B94DC4D0B4B4503283133B8D73302DCAE1D83260B136699B8DFBD34893F30734AF3DAF9BA370F9842E1BAC724C9AD4E3D7C3166DFF1AE5B4A1FD53AB9A1148CC94F82350B97EA95DD2852C08959863CDE35418AD66A18D1F7D952DD909BE0646DBE6106598A8A78033231625AFF28FD48F2320483F92EE6E6ECD86062188FB1D0F23AD9E8DE075608F592CB4261AF3BB3FABD1E822005F2E9FD2082F62AEA32FA6584253C375ACC18D4B0AC9DBC649EC9D9CDAD64982F0DF6DB96829F2E2D03E388B59A32B0326572A4870A1D0153590A753E0D94AC996D1F8F46F2D5E20386841D22E98C9CA151672C05E0748F035E3374ABA8BEEE532CFCB4A986EC48B1D203EC27C2D7F1192D76AFCB03CF23E1F3545E732855670E0685DDC9452E684EFB229F828F9D29D4B7AF65FA957B869096F306C6C78B8D33902BC6FB65A754C881D4E859AB076A7B5197DCDBADD91CEA41DD6F20A39FE14C603FC8B14D40F27CE3D4CD11DF9CAE4142A9EB898EADE48129460173012CEB7DD3210B60C5A5DF29F1B69C142AFA02387110C89E8AAED9EBC0818095173D8FC6
 * AES 解密后为：[{"key":"id","value":"-1","equals":true,"description":"","enabled":true},{"key":"name","value":"A","equals":true,"description":"","enabled":true},{"key":"tel","value":"","equals":true,"description":"","enabled":true},{"key":"balance","value":"-1","equals":true,"description":"","enabled":true},{"key":"timestamp","value":"","equals":true,"description":"","enabled":true},{"key":"tablename","value":"test1","equals":true,"description":"","enabled":true},{"key":"offset","value":"0","equals":true,"description":"","enabled":true},{"key":"limit","value":"10","equals":true,"description":"","enabled":true}]
 * AES 加密后为：77758ABE68834A2EC957BF3A927F407A8DDB80F0E86F02CC7FCB40F19E2D1C7D7C52AC4DF39A91FCD43CD7777893E327B221A248F3494CABAA2B94DC4D0B4B4503283133B8D73302DCAE1D83260B1366802D57B0C56E64E2F9F7D6CC45E1D9A3C2BC065DCFFFD103CE1BCCBF62CA89801C46F4D6BEC3279AB7C05E8316176AB89C9AC5E3A95FA09AB4281CCEC68556268774268264D40BBD31F64DA3B87D03FB9C01AED99FD94FE1240C4F982344F8C07C52AC4DF39A91FCD43CD7777893E327B221A248F3494CABAA2B94DC4D0B4B4503283133B8D73302DCAE1D83260B136699B8DFBD34893F30734AF3DAF9BA370F9842E1BAC724C9AD4E3D7C3166DFF1AE5B4A1FD53AB9A1148CC94F82350B97EA95DD2852C08959863CDE35418AD66A18D1F7D952DD909BE0646DBE6106598A8A78033231625AFF28FD48F2320483F92EE6E6ECD86062188FB1D0F23AD9E8DE075608F592CB4261AF3BB3FABD1E822005F2E9FD2082F62AEA32FA6584253C375ACC18D4B0AC9DBC649EC9D9CDAD64982F0DF6DB96829F2E2D03E388B59A32B0326572A4870A1D0153590A753E0D94AC996D1F8F46F2D5E20386841D22E98C9CA151672C05E0748F035E3374ABA8BEEE532CFCB4A986EC48B1D203EC27C2D7F1192D76AFCB03CF23E1F3545E732855670E0685DDC9452E684EFB229F828F9D29D4B7AF65FA957B869096F306C6C78B8D33902BC6FB65A754C881D4E859AB076A7B5197DCDBADD91CEA41DD6F20A39FE14C603FC8B14D40F27CE3D4CD11DF9CAE4142A9EB898EADE48129460173012CEB7DD3210B60C5A5DF29F1B69C142AFA02387110C89E8AAED9EBC0818095173D8FC6
 * AES 解密后为：[{"key":"id","value":"-1","equals":true,"description":"","enabled":true},{"key":"name","value":"A","equals":true,"description":"","enabled":true},{"key":"tel","value":"","equals":true,"description":"","enabled":true},{"key":"balance","value":"-1","equals":true,"description":"","enabled":true},{"key":"timestamp","value":"","equals":true,"description":"","enabled":true},{"key":"tablename","value":"test1","equals":true,"description":"","enabled":true},{"key":"offset","value":"0","equals":true,"description":"","enabled":true},{"key":"limit","value":"10","equals":true,"description":"","enabled":true}]
 * 
 * select fuzzy results
 * AES 加密后为：4C1B78156742396926D19E66243C7BB48D6E203144BC51023F043D7BCF7FD4B22D6A8D1960AA1C1083C693B8F0AB998CFF63BB62CE4FE72669219A3260C961DF9EB20F5E00A6803C4B08C58550442355FF82DF9FC45D273C9D1B67D38783B72C003DBC7E5BF3F74F1F8EBF0702DC3B7FAA9FA9723E5778658BF70025720EF703F3A6600920F791EF3BB795C81232C5E25BABD23FB7B2DD9D413C7C0A76EDD8E26A7971457C84A5401555A78525A7A7CBC5615A4EBB415DFF3D0B366E3D590A19C4C29D3CD2ECFB076FDFB98DCC63CF8C2F68D723455F9FBFA36FF53F649E9AB01FE1E82D4D2B5FA1CAF5D225A7DD8B91FEC6423DD895DE9076FE46539B0EFA389D279128407C1AB72AD384410BA16BCFD2A15CC3F2DE2BBEA13B3B29948FE052872FECBAD88B115D13C7B65FF1F9085A6E4834590CD21B4A1A0E796427D419397250636E1395287B63654AFFC06202ACCC445A172F36E294C7B98B0BF1E0A315F0368A41805528AE4851F8AD14E49A4409A9A066D18CC463BCF134F330A92713E141F0E885F8A6FA997AEF1B2FA7C4BE4B9D7676FF0885957D0110051E7C09799413D42C038E5EB8FB1164E580EF458B
 * AES 解密后为：{"info":[{"Tel":"34567890123","ID":"4","Balance":"44.0","Name":"Catd","timestamp":{"date":18,"day":1,"hours":14,"minutes":31,"month":11,"nanos":0,"seconds":52,"time":1513578712000,"timezoneOffset":-480,"year":117}},{"Tel":"12345678901","ID":"1","Balance":"14","Name":"Ana","timestamp":{"date":5,"day":2,"hours":9,"minutes":55,"month":11,"nanos":0,"seconds":0,"time":1512438900000,"timezoneOffset":-480,"year":117}}]}
 * AES 加密后为：4C1B78156742396926D19E66243C7BB48D6E203144BC51023F043D7BCF7FD4B22D6A8D1960AA1C1083C693B8F0AB998CFF63BB62CE4FE72669219A3260C961DF9EB20F5E00A6803C4B08C58550442355FF82DF9FC45D273C9D1B67D38783B72C003DBC7E5BF3F74F1F8EBF0702DC3B7FAA9FA9723E5778658BF70025720EF703F3A6600920F791EF3BB795C81232C5E25BABD23FB7B2DD9D413C7C0A76EDD8E26A7971457C84A5401555A78525A7A7CBC5615A4EBB415DFF3D0B366E3D590A19C4C29D3CD2ECFB076FDFB98DCC63CF8C2F68D723455F9FBFA36FF53F649E9AB01FE1E82D4D2B5FA1CAF5D225A7DD8B91FEC6423DD895DE9076FE46539B0EFA389D279128407C1AB72AD384410BA16BCFD2A15CC3F2DE2BBEA13B3B29948FE052872FECBAD88B115D13C7B65FF1F9085A6E4834590CD21B4A1A0E796427D419397250636E1395287B63654AFFC06202ACCC445A172F36E294C7B98B0BF1E0A315F0368A41805528AE4851F8AD14E49A4409A9A066D18CC463BCF134F330A92713E141F0E885F8A6FA997AEF1B2FA7C4BE4B9D7676FF0885957D0110051E7C09799413D42C038E5EB8FB1164E580EF458B
 * AES 解密后为：{"info":[{"Tel":"34567890123","ID":"4","Balance":"44.0","Name":"Catd","timestamp":{"date":18,"day":1,"hours":14,"minutes":31,"month":11,"nanos":0,"seconds":52,"time":1513578712000,"timezoneOffset":-480,"year":117}},{"Tel":"12345678901","ID":"1","Balance":"14","Name":"Ana","timestamp":{"date":5,"day":2,"hours":9,"minutes":55,"month":11,"nanos":0,"seconds":0,"time":1512438900000,"timezoneOffset":-480,"year":117}}]}
 *
 */





public class EncrypetionFilter implements Filter {


	private static final String password = "545253234";
	
	private PrintWriter printWriter;

	/**
	 * Default constructor. 
	 */
	public EncrypetionFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("EncrypetionFilter.destory()*************************************************************************************");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain

		request=new HttpRequestWapper((HttpServletRequest)request);
		
		chain.doFilter(request, response);
				

		String output =  response.toString();
		byte[] encodeMessage =  AESUtil.AESJDKEncode(output, password);
		output = AESUtil.byte2hex(encodeMessage);
		printWriter = response.getWriter();	
		printWriter.print(output);

	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("EncrypetionFilter.init()********************************************************************************");
	}

	//内部类
	class HttpRequestWapper extends HttpServletRequestWrapper{

		public HttpRequestWapper(HttpServletRequest request) {
			super(request);
		}

		@Override
		public String[] getParameterValues(String name) {
			String[] strs=super.getParameterValues(name);
			for(int i=0;i<strs.length;i++) {
				byte[] Message = AESUtil.hex2byte(strs[i]);
				byte[] decodeMessage =  AESUtil.AESJDKDecode(Message, EncrypetionFilter.password);  //AES解密
				strs[i] = new String(decodeMessage);
			}
			return strs;
		}
	}

//	class HttpResponseWapper extends HttpServletResponseWrapper {
//		private PrintWriter printWriter;  
//		public HttpResponseWapper(HttpServletResponse response) {
//			super(response);
//			// TODO Auto-generated constructor stub
//		}
//		public PrintWriter getWriter() throws IOException {  
//
//			
//			
//			
//			return printWriter;  
//		}  
//	}
	
}