package org.loganalyzer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.loganalyzer.model.Log;
import org.loganalyzer.utils.LogUtils;

public class LogAnalyzer {

  public static void main(String[] args) {
    List<Log> logs = new ArrayList<>();
    try {
      logs = LogUtils.fetchLogs("src/main/resources/log.csv");
    } catch (IOException e) {
      System.out.println("CSV reading failed");
    }

    System.out.println("Most Recent Record From Log CSV :");
    System.out.println(Objects.requireNonNull(LogUtils.fetchRecentLog(logs)).toString());

    System.out.println("Last Error log is :");
    System.out.println(Objects.requireNonNull(LogUtils.fetchLastError(logs)).toString());

    System.out.println("List of errors matching error message FAILED is :");
    List<Log> errorLogs = LogUtils.fetchLogsFromErrorMessage(logs,"FAILED");
    assert errorLogs != null;
    for(Log log: errorLogs){
      System.out.println(log.toString());
    }
  }
}