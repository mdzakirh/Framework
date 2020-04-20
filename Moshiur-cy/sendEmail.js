
const fs = require('fs');
const path = require("path");

const config = require("./cypress/config/config")
const _ = require('lodash')
const reportFilePath = './mochawesome.json';

let result = []
let tests
let failures
let passes
let pending
let skipped
let emailSubject = '';
let emailBody = '';

let rootDir = path.resolve("./");

try {
  if (fs.existsSync(reportFilePath)) {
    const data = require(reportFilePath);
    tests = _.flatMapDeep(_.map(data.results, x => _.map(x.suites, y => y.tests)));
    failures = _.filter(tests, z => z.fail);
    passes = _.filter(tests, z => z.pass);
    pending = _.filter(tests, z => z.pending);
    skipped = _.filter(tests, z => z.skipped);

    if (data.stats.failures > 0) {
      // failures = data.failures
      emailSubject = '❌ ' + data.stats.failures + ' test(s) failed! AppUrl = ' + config.appUrl

      emailBody += failures.map((failure) => {
        return "\n <p>❌ " + failure.title + '</p>'
      }).join(" ");
    } else {
      emailSubject = '✔ No failing test! AppUrl = ' + config.appUrl
    }

    var stat = data.stats;
    emailBody += '\n <p>Total=' + stat.tests + ', Passed=' + stat.passes + ', Failed=' + stat.failures + ', Skipped=' + stat.skipped + ', Pending=' + stat.pending + '</p>';

    emailBody += passes.map((pass) => {
      return "\n <p>✔ " + pass.title + '</p>'
    }).join(" ");

    emailBody += pending.map((t) => {
      return "\n <p>Pending: " + t.title + '</p>'
    }).join(" ");

    emailBody += skipped.map((t) => {
      return "\n <p>Skipped: " + t.title + '</p>'
    }).join(" ");

  } else {

    emailSubject = 'Something wrong! AppUrl = ' + config.appUrl
    emailBody += "<p>❌ No report generated!</p>";
  }

} catch (error) {
  emailSubject = 'ERROR! AppUrl = ' + config.appUrl
  emailBody += "<p>❌ EXCEPTION: " + error.message + "\n" + "stack trace: \n" + error.stack + '</p>';
}

var buildMachineInfo = "COMPUTERNAME=" + process.env.COMPUTERNAME + ", USERNAME=" + process.env.USERNAME + ", USERDOMAIN=" + process.env.USERDOMAIN + ", Build Location=" + path.resolve("./")
emailBody += "<p>" + buildMachineInfo + "</p>";


const nodemailer = require("nodemailer");

// async..await is not allowed in global scope, must use a wrapper
async function main() {
  // Generate test SMTP service account from ethereal.email
  // Only needed if you don't have a real mail account for testing
  let testAccount = await nodemailer.createTestAccount();

  // create reusable transporter object using the default SMTP transport
  let transporter = nodemailer.createTransport({
    host: "",
    port: 000,
    secure: false, // true for 465, false for other ports
    auth: {
      user: '', // generated ethereal user
      pass: '' // generated ethereal password
    }
  });

  // send mail with defined transport object
  let info = await transporter.sendMail({
    from: '', // sender address
    to: "", // list of receivers
    subject: emailSubject, // Subject line
    // text: "Hello world?", // plain text body
    html: emailBody // html body
  });

  console.log("Message sent: %s", info.messageId);
  // Message sent: <b658f8ca-6296-ccf4-8306-87d57a0b4321@example.com>

  // Preview only available when sending through an Ethereal account
  console.log("Preview URL: %s", nodemailer.getTestMessageUrl(info));
  // Preview URL: https://ethereal.email/message/WaQKMgKddxQDoou...
}

main().catch(console.error);


