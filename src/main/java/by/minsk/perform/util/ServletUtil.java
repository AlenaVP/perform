package by.minsk.perform.util;

import by.minsk.perform.web.SecurityUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Alena_Papruha
 * @version 1.1
 * @since 20 Dec, 2020
 */

@Component
public class ServletUtil implements Serializable {

    public long getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    public Long getPerformerId(HttpServletRequest req) {
        String parameter = req.getParameter("performerId");
        long performerId;
        if ((parameter == null) || parameter.isEmpty()) {
            performerId = SecurityUtil.authPerformerId();
        } else {
            performerId = Long.parseLong(parameter);
            SecurityUtil.setAuthPerformerId(performerId);
        }
        return performerId;
    }

    public String[] getNewTrackArray(HttpServletRequest req) {
        String[] sourceTracks = Util.getTrackArray(req.getParameter("tracks"));
        String[] newTracks;
        String trackName = req.getParameter("trackName");
        if (sourceTracks.length == 1 && (sourceTracks[0] == null || sourceTracks[0].isEmpty())) {
            newTracks = new String[1];
            newTracks[0] = trackName;
        } else {
            newTracks = new String[sourceTracks.length + 1];
            System.arraycopy(sourceTracks, 0, newTracks, 0, sourceTracks.length);
            newTracks[newTracks.length - 1] = trackName;
        }
        return newTracks;
    }

    public String[] getArrayAfterTrackDeleting(HttpServletRequest req, String[] sourceTracks) {
        String[] newTracks = new String[sourceTracks.length - 1];
        String track = req.getParameter("deleted");
        if (sourceTracks.length > 1) {
            for (int i = 0; i < sourceTracks.length; i++) {
                if (sourceTracks[i].equals(track)) {
                    if (i == 0) {
                        System.arraycopy(sourceTracks, i + 1, newTracks, i, sourceTracks.length - 1);
                    } else if (i == sourceTracks.length - 1) {
                        System.arraycopy(sourceTracks, 0, newTracks, 0, sourceTracks.length - 1);
                    } else {
                        System.arraycopy(sourceTracks, 0, newTracks, 0, i);
                        System.arraycopy(sourceTracks, i + 1, newTracks, i, sourceTracks.length - i - 1);
                    }
                    break;
                }
            }
        }
        return newTracks;
    }
}