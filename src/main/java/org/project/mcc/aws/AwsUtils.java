package org.project.mcc.aws;

import java.io.IOException;

public class AwsUtils {

    public static void saveFileInS3(final String localPath) throws IOException {
        S3Helper s3Helper = new S3Helper();
        s3Helper.saveFileInS3(localPath);
    }


}
