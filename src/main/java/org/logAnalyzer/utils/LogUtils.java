package org.loganalyzer.utils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.loganalyzer.model.Log;

public class LogUtils {

  public static Log fetchRecentLog(List<Log> logs) {
    if (logs.isEmpty())
        return null;
    Log recentLog = logs.get(0);
    for (Log log: logs) {
      if (recentLog.getTimestamp() < log.getTimestamp()) {
        recentLog = log;
      }
    }
    return recentLog;
  }
  public static Long fetchLastError(List<Log> logs) {
    if (logs.isEmpty())
      return null;
    Long lastError = null;
    for (Log log: logs) {
      if (log.getLogType().equals("Error")) {
        if (lastError!=null) {
          if (lastError < log.getTimestamp()) {
            lastError = log.getTimestamp();
          }
        }
        else {
          lastError = log.getTimestamp();
        }
      }
    }
    return lastError;
  }
  public static List<Log> fetchLogs(String filepath) throws IOException {
    Reader reader = Files.newBufferedReader(Paths.get(filepath));
    CsvToBean<Log> csvToBean = new CsvToBeanBuilder<Log>(reader)
        .withType(Log.class)
        .withIgnoreLeadingWhiteSpace(true)
        .build();
    return csvToBean.parse();
  }
  public static List<Log> fetchLogsFromErrorMessage(List<Log>logs, String errorMessage) {
    if (logs.isEmpty())
      return null;
    List<Log> errorLogs = new ArrayList<>();
    for (Log log: logs) {
      if (log.getMessage().equals(errorMessage)) {
        errorLogs.add(log);
      }
    }
    return errorLogs;
  }
}
