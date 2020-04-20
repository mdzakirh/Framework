
const SlackWebhook = require('slack-webhook')
const slack = new SlackWebhook("<your webhook url>")

const fs = require('fs');
const path = require("path");
const config = require("./cypress/config/config")
const _ = require('lodash')
const reportFilePath = './mochawesome.json';

let result = []
let tests
let failures
let title

let rootDir = path.resolve("./");

try {
  if (fs.existsSync(reportFilePath)) {
    const data = require(reportFilePath);
    tests = _.flatMapDeep(_.map(data.results, x => _.map(x.suites, y => y.tests)));
    failures = _.filter(tests, z => z.fail);

    if (data.stats.failures > 0) {
      // failures = data.failures
      title = '<!channel> ' + data.stats.failures + ' test(s) failed! AppUrl = ' + config.appUrl

      result = failures.map((failure) => {
        return {
          "fallback": "Test summary: " + failure.title,
          "color": "danger",
          "text": ":x: " + failure.title,
        }
      });
    } else {
      title = ':smile: No failing test! AppUrl = ' + config.appUrl
      result = [
        {
          "fallback": "Test summary",
          "color": "good",
          "text": ":white_check_mark: All tests pass!"
        }
      ]
    }

    var stat = data.stats;
    result.push({
      "text": 'Total=' + stat.tests + ', Passed=' + stat.passes + ', Failed=' + stat.failures + ', Skipped=' + stat.skipped + ', Pending=' + stat.pending
    });

  } else {

    title = '<!channel> Something wrong! AppUrl = ' + config.appUrl
    result.push({
      "fallback": "No report generated!",
      "color": "danger",
      "text": ":x: No report generated!"
    });
  }

} catch (error) {
  title = '<!channel> ERROR! AppUrl = ' + config.appUrl
  result.push({
    "fallback": error.toString(),
    "color": "danger",
    "text": ":x: EXCEPTION: " + error.message + "\n" + "stack trace: \n" + error.stack
  });
}


// slack.send({
//   text: title,
//   attachments: result
// })


