package utils.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.MultiPartSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;

import java.util.List;
import java.util.Map;

public class ReportingFilter implements Filter {
    private static final Logger consoleLogger = LogManager.getLogger(ReportingFilter.class);
    private final Reporter reporter;

    public ReportingFilter(ITestContext context) {
        this.reporter = (Reporter) context.getAttribute(Reporter.REPORTER);
    }

    private void report(String requestParams, String requestBody, String responseParams, String responseBody) {
        this.reporter.log(requestParams, requestBody, responseParams, responseBody);
    }

    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);
        String var10000 = requestSpec.getMethod();
        String requestParams = "\ud83c\udfa0  \ud83c\udfa0  \ud83c\udfa0  \ud83c\udfa0  \ud83c\udfa0   REQUEST \ud83c\udfa0  \ud83c\udfa0  \ud83c\udfa0  \ud83c\udfa0  \ud83c\udfa0  \nRequest method:\t" + var10000 + "\nRequest URI:\t" + requestSpec.getURI() + "\nQuery params:\t" + formatMap(requestSpec.getQueryParams()) + "\nRequest params:\t" + formatMap(requestSpec.getRequestParams()) + "\nForm params:\t" + formatMap(requestSpec.getFormParams()) + "\nPath params:\t" + formatMap(requestSpec.getPathParams()) + "\nHeaders:\t\t" + formatHeaders(requestSpec.getHeaders()) + "\n--------------------------------------------------------------------------------------\nCookies:\t\t" + formatCookies(requestSpec.getCookies()) + "\nMultiparts:\t\t" + formatMultiPartParams(requestSpec.getMultiPartParams()) + "\n--------------------------------------------------------------------------------------\nRequest Body: ";


        String requestBody = requestSpec.getBody() != null ? this.getBodyAsPrettyString(requestSpec) : "<none>";
        consoleLogger.info("\n" + requestParams + "\t" + requestBody + "\n\ud83c\udfa0  \ud83c\udfa0  \ud83c\udfa0  \ud83c\udfa0  \ud83c\udfa0   End Of Request \ud83c\udfa0  \ud83c\udfa0  \ud83c\udfa0  \ud83c\udfa0  \ud83c\udfa0  ");
        if (requestBody.equals("none")) {
            requestParams = requestParams.concat(requestBody);
        }

        int var9 = response.getStatusCode();
        String responseMsg = "\n\ud83d\ude2e\u200d\ud83d\udca8  \ud83d\ude2e\u200d\ud83d\udca8  \ud83d\ude2e\u200d\ud83d\udca8   RESPONSE \ud83d\ude2e\u200d\ud83d\udca8  \ud83d\ude2e\u200d\ud83d\udca8  \ud83d\ude2e\u200d\ud83d\udca8  \nResponse Status:\t\t" + var9 +"\nResponse Time:\t\t\t" + response.time() +" MS"+ "\nResponse Status Line:\t" + response.getStatusLine().trim() + "\nResponse Headers:\n\t\t\t\t" + formatHeaders(response.getHeaders()) + "\nResponse Cookies:\n\t\t\t\t" + formatMap(response.getCookies()) + "\n--------------------------------------------------------------------------------------\nResponse Body: ";
        consoleLogger.info(responseMsg);
        consoleLogger.info(" \n" + response.getBody().asPrettyString() + "\n\ud83d\ude2e\u200d\ud83d\udca8  \ud83d\ude2e\u200d\ud83d\udca8  \ud83d\ude2e\u200d\ud83d\udca8   End of Response \ud83d\ude2e\u200d\ud83d\udca8  \ud83d\ude2e\u200d\ud83d\udca8  \ud83d\ude2e\u200d\ud83d\udca8  ");
        String respBody = response.getBody() != null ? response.getBody().asPrettyString() : "none";
        this.report(requestParams, requestBody, responseMsg, respBody);
        return response;
    }

    private String getBodyAsPrettyString(FilterableRequestSpecification requestSpec) {
        Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString((String) requestSpec.getBody());
        return "\n" + gson.toJson(je);
    }

    public static String formatMap(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "<none>";
        }
        StringBuilder sb = new StringBuilder();
        map.forEach((key, value) -> sb.append(key).append("=").append(value).append("\n\t\t\t\t"));
        return sb.toString().trim();
    }

    public static String formatHeaders(Headers headers) {
        if (!headers.exist()) {
            return "<none>";
        }
        StringBuilder sb = new StringBuilder();
        for (Header header : headers) {
            sb.append(header.getName()).append("=").append(header.getValue()).append("\n\t\t\t\t");
        }
        return sb.toString().trim();
    }

    public static String formatCookies(Cookies cookies) {
        if (cookies == null || cookies.size() == 0) {
            return "<none>";
        }
        StringBuilder sb = new StringBuilder();
        for (Cookie cookie : cookies) {
            sb.append(cookie.getName()).append("=").append(cookie.getValue()).append("\n\t\t\t\t");
        }
        return sb.toString().trim();
    }

    public static String formatMultiPartParams(List<MultiPartSpecification> multiPartParams) {
        if (multiPartParams == null || multiPartParams.isEmpty()) {
            return "<none>";
        }
        StringBuilder sb = new StringBuilder();
        for (MultiPartSpecification param : multiPartParams) {
            sb.append(param.getControlName()).append("=").append(param.getContent()).append("\n\t\t\t\t");
        }
        return sb.toString().trim();
    }
}
