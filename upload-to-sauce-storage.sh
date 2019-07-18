#!/bin/bash

curl -u ${SAUCE_USERNAME}:${SAUCE_ACCESS_KEY} -X POST -H "Content-Type: application/octet-stream" https://saucelabs.com/rest/v1/storage/${SAUCE_USERNAME}/LoanCalc.apk?overwrite=true --data-binary @./app/build/outputs/apk/debug/app-debug.apk


