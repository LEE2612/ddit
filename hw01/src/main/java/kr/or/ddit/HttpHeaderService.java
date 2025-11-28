package kr.or.ddit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpHeaderService {

    private static final String MDN_URL = "https://developer.mozilla.org/ko/docs/Web/HTTP/Reference/Headers";
    private static final String USER_AGENT = "Mozilla/5.0";

    public List<HeaderInfo> getHttpHeaders() {
        List<HeaderInfo> list = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(MDN_URL)
                                .userAgent(USER_AGENT)
                                .referrer("https://developer.mozilla.org")
                                .get();

            // <main> 안의 모든 <dl> 가져오기
            Elements dlItems = doc.select("main dl");

            for (Element dl : dlItems) {
                Elements dts = dl.select("dt");
                Elements dds = dl.select("dd");

                for (int i = 0; i < dts.size(); i++) {
                    String name = dts.get(i).text().trim();
                    String desc = i < dds.size() ? dds.get(i).text().trim() : "";

                    if (!name.isEmpty()) {
                        list.add(new HeaderInfo(name, desc));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
