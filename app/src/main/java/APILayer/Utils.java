package APILayer;

import android.support.annotation.NonNull;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static String parseQueryWithHashtags(String query, @NonNull HashSet<String> hashtags) {
        Pattern hashtagPattern = Pattern.compile("(#)[^\\s#]+");
        Matcher matcher = hashtagPattern.matcher(query);

        while (matcher.find()) {
            String foundTag = matcher.group();
            query = query.replace(foundTag, "");
            hashtags.add(foundTag);
        }

        return query.trim();
    }
}
