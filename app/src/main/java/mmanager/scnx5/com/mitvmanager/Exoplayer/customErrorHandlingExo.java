package mmanager.scnx5.com.mitvmanager.Exoplayer;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;

import java.io.FileNotFoundException;
import java.io.IOException;

public class customErrorHandlingExo extends DefaultLoadErrorHandlingPolicy {

    @Override
    public long getRetryDelayMsFor(
            int dataType,
            long loadDurationMs,
            IOException exception,
            int errorCount) {
        return exception instanceof FileNotFoundException
                ? C.TIME_UNSET
                : super.getRetryDelayMsFor(
                dataType, loadDurationMs, exception, errorCount);
    }

    @Override
    public int getMinimumLoadableRetryCount(int dataType) {
        return Integer.MAX_VALUE;
    }



}