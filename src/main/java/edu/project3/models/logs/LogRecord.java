package edu.project3.models.logs;

import edu.project3.models.http.HttpMethod;
import edu.project3.models.http.HttpStatusCode;
import java.time.OffsetDateTime;

public record LogRecord(
    String remoteAddress,
    String remoteUser,
    OffsetDateTime timeLocal,
    HttpMethod method,
    String request,
    String protocol,
    HttpStatusCode status,
    long bodyBytesSent,
    String httpReferer,
    String httpUserAgent
) {
}
