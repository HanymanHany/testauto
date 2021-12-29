package helpers;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    public static RequestSpecification RequestSpec(String baseUrl) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
    public static String requestPost(String email){
        String response = given()
                .when()
                .queryParam("token","0572c387c1b342a117d6a19aff7408e6")
                .queryParam("email",email)
                .post().then().log().all()
                .assertThat().statusCode(200).extract()
                .asString();
        return response;
    }

    public static String getParamJson(String param)  {
        return param.replaceAll("\\[\"|\"\\]", "");

    }
    public static String clearQuery(String param)  {
        return param.replaceAll("\\[|\\]", "");

    }
    public static Map<String, List<String>> getParamUrl(URL url) throws UnsupportedEncodingException {
        System.out.println(url);
        final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
        final String[] pairs = url.getQuery().split("&");
        for (String pair : pairs) {
            final int idx = pair.indexOf("=");
            final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
            if (!query_pairs.containsKey(key)) {
                query_pairs.put(key, new LinkedList<String>());
            }
            final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
            query_pairs.get(key).add(value);
        }
        return query_pairs;
    }
}
