package com.githubsample.api;

import com.google.gson.annotations.SerializedName;

import retrofit2.Response;

/**
 *
 * Created from
 * https://github.com/eclipse/egit-github/blob/master/org.eclipse.egit.github.core/src/org/eclipse/egit/github/core/client/PageLinks.java
 */
public class LinkHeaderParser {

    private static final String HEADER_LINK = "Link"; //$NON-NLS-1$
    private static final String HEADER_NEXT = "X-Next"; //$NON-NLS-1$

    private static final String DELIM_LINKS = ","; //$NON-NLS-1$
    private static final String DELIM_LINK_PARAM = ";"; //$NON-NLS-1$

    private static final String META_REL = "rel"; //$NON-NLS-1$
    private static final String META_NEXT = "next"; //$NON-NLS-1$

    public static String getNexPage(Response response) {
        String linkHeader = response.headers().get(HEADER_LINK);
        if (linkHeader != null) {
            String[] links = linkHeader.split(DELIM_LINKS);
            for (String link : links) {
                String[] segments = link.split(DELIM_LINK_PARAM);
                if (segments.length < 2)
                    continue;

                String linkPart = segments[0].trim();
                if (!linkPart.startsWith("<") || !linkPart.endsWith(">")) //$NON-NLS-1$ //$NON-NLS-2$
                    continue;
                linkPart = linkPart.substring(1, linkPart.length() - 1);

                for (int i = 1; i < segments.length; i++) {
                    String[] rel = segments[i].trim().split("="); //$NON-NLS-1$
                    if (rel.length < 2 || !META_REL.equals(rel[0]))
                        continue;

                    String relValue = rel[1];
                    if (relValue.startsWith("\"") && relValue.endsWith("\"")) //$NON-NLS-1$ //$NON-NLS-2$
                        relValue = relValue.substring(1, relValue.length() - 1);

                    if (META_NEXT.equals(relValue))
                        return linkPart;
                }
            }
        } else {
            return response.headers().get(HEADER_NEXT);
        }

        return null;
    }


}
