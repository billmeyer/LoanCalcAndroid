#!/bin/bash

APPAPK=./app/build/outputs/apk/debug/app-debug.apk
TESTAPK=./app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk
DEVICE1='Google Pixel GoogleAPI Emulator,platformVersion=7.1'
DEVICE2='LG Nexus 4 GoogleAPI Emulator,platformVersion=4.4'

# Run all test:
# sauce-runner-virtual --framework espresso --user ${SAUCE_USERNAME} --api-key ${SAUCE_ACCESS_KEY} --app ${APPAPK} --test-app ${TESTAPK} \
    # --devices "deviceName='${DEVICE1}'" --devices "deviceName='${DEVICE2}'" $*

# Run a filtered set of tests
sauce-runner-virtual --framework espresso --user ${SAUCE_USERNAME} --api-key ${SAUCE_ACCESS_KEY} --app ${APPAPK} --test-app ${TESTAPK} \
    --devices "deviceName='${DEVICE1}'" --devices "deviceName='${DEVICE2}'" \
    --include-tests "class io.billmeyer.loancalc.LoanCalcTest#calcLoanViaUI" $*

