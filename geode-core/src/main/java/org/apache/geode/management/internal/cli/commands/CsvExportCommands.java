package org.apache.geode.management.internal.cli.commands;



import org.apache.geode.cache.Cache;
import org.apache.geode.cache.CacheClosedException;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.execute.FunctionInvocationTargetException;
import org.apache.geode.distributed.DistributedMember;
import org.apache.geode.internal.security.IntegratedSecurityService;
import org.apache.geode.internal.security.SecurityService;
import org.apache.geode.management.cli.CliMetaData;
import org.apache.geode.management.cli.ConverterHint;
import org.apache.geode.management.cli.Result;
import org.apache.geode.management.internal.cli.CliUtil;
import org.apache.geode.management.internal.cli.i18n.CliStrings;
import org.apache.geode.management.internal.cli.result.ResultBuilder;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;

/**
 * 
 * @author Gregory Green
 *
 */
public class CsvExportCommands implements CommandMarker {
  /**
   * Export Csv Commands
   */
  public CsvExportCommands() {

  }// ------------------------------------------------

  private SecurityService securityService = IntegratedSecurityService.getSecurityService();

  @CliCommand(value = CsvStrings.EXPORT_CSV, help = CsvStrings.EXPORT_CSV_HELP)
  @CliMetaData(relatedTopic = {CsvStrings.TOPIC_GEODE_DATA, CliStrings.TOPIC_GEODE_REGION})
  public Result exportData(
      @CliOption(key = CsvStrings.EXPORT_CSV_REGION, mandatory = false,
          optionContext = ConverterHint.REGIONPATH,
          help = CsvStrings.EXPORT_CSV_REGION_HELP) String regionName,
      @CliOption(key = CsvStrings.EXPORT_CSV_FILE,
          unspecifiedDefaultValue = CliMetaData.ANNOTATION_NULL_VALUE, mandatory = true,
          help = CsvStrings.EXPORT_CSV_FILE_HELP) String filePath,
      @CliOption(key = CsvStrings.EXPORT_CSV_MEMBER,
          unspecifiedDefaultValue = CliMetaData.ANNOTATION_NULL_VALUE,
          optionContext = ConverterHint.MEMBERIDNAME, mandatory = true,
          help = CsvStrings.EXPORT_CSV_MEMBER_HELP) String memberNameOrId) {

    // check permission
    this.securityService.authorizeRegionRead(regionName);

    // get cache instance
    final Cache cache = CacheFactory.getAnyInstance();

    final DistributedMember targetMember = CliUtil.getDistributedMemberByNameOrId(memberNameOrId);
    Result result = null;

    if (!filePath.endsWith(CsvStrings.CSV_FILE_EXTENSION)) {
      return ResultBuilder.createUserErrorResult(
          CliStrings.format(CsvStrings.INVALID_FILE_EXTENSION, CsvStrings.CSV_FILE_EXTENSION));
    }
    try {

      exportCSV(regionName, cache);

    } catch (CacheClosedException e) {
      result = ResultBuilder.createGemFireErrorResult(e.getMessage());
    } catch (FunctionInvocationTargetException e) {
      result = ResultBuilder.createGemFireErrorResult(
          CliStrings.format(CliStrings.COMMAND_FAILURE_MESSAGE, CsvStrings.EXPORT_CSV));
    }

    return result;
  }

  void exportCSV(String regionName, Cache cache) {
    System.out.println("Hello");

  }
}
