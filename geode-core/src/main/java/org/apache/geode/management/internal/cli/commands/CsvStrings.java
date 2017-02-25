package org.apache.geode.management.internal.cli.commands;

import org.apache.geode.management.internal.cli.i18n.CliStrings;

public interface CsvStrings {
  static final String EXPORT_CSV = "exportCSV";

  static final String EXPORT_CSV_HELP = "export region data in CSV format";
  static final String TOPIC_GEODE_DATA = CliStrings.TOPIC_GEODE_DATA;

  static final String EXPORT_CSV_REGION = "region";

  static final String EXPORT_CSV_REGION_HELP = "Region from which CSV data will be exported.";
  static final String EXPORT_CSV_FILE = "file";
  static final String EXPORT_CSV_FILE_HELP =
      "File to which the exported CSV will be written. The file must have an extension of \".csv\".";

  static final String EXPORT_CSV_MEMBER = "member";
  static final String EXPORT_CSV_MEMBER_HELP =
      "Name/Id of a member which hosts the region. The data will be exported to the specified file on the host where the member is running.";
  static final String EXPORT_CSV_MEMBER_NOT_FOUND = "Member {0} not found";
  static final String EXPORT_CSV_REGION_NOT_FOUND = "Region {0} not found";
  static final String EXPORT_CSV_SUCCESS_MESSAGE =
      "CSV succesfully exported from region : {0} to file : {1} on host : {2}";

  static final String CSV_FILE_EXTENSION = ".csv";

  public static final String INVALID_FILE_EXTENSION = CliStrings.INVALID_FILE_EXTENSION;
}
