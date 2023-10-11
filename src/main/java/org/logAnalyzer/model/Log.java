package org.loganalyzer.model;

public class Log {
  String logType;
  Long timestamp;
  String message;

  public Log(String logType, Long timestamp, String message) {
    this.logType = logType;
    this.timestamp = timestamp;
    this.message = message;
  }

  public String getLogType() {
    return logType;
  }

  public void setLogType(String logType) {
    this.logType = logType;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "Log{" +
            "logType='" + logType + '\'' +
            ", timestamp=" + timestamp +
            ", message='" + message + '\'' +
            '}';
  }
}
