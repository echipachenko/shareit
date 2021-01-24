'use strict';

module.exports = {
  NODE_ENV: '"production"',
  SERVER_API_URL: '""',
  BUILD_TIMESTAMP: `'${new Date().getTime()}'`,
  // APP_VERSION is passed as an environment variable from the Gradle / Maven build tasks.
  VERSION: `'${process.env.hasOwnProperty('APP_VERSION') ? process.env.APP_VERSION : 'UNKNOWN'}'`,
  RECAPTCHA_ENABLED: 'true',
  RECAPTCHA_SITE_KEY: '"6LfWbDoaAAAAAED4zUrqmFsdI5yFwSAwn4R1a9oC"',
};
