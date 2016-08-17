package com.githubsample.transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rory_McCormack on 16/08/2016.
 */
public abstract class AbstractTransformer<In, Out> {

    public abstract Out transform(In in);

    public List<Out> transform(List<In> inList) {
        final List<Out> outList = new ArrayList<>();

        if (inList == null) {
            return outList;
        }

        for (In in : inList) {
            Out out = transform(in);
            if (out != null) {
                outList.add(out);
            }
        }

        return outList;
    }
}
