# Test Plan v2
**Updated on Oct.15, 2023**

**Author**: SDP Team 160

## 1 Testing Strategy
### 1.1 Overall strategy
#### 1.1.1 Unit testing
The software contains components of UI, database, and code. UI needs to be neat and clean, logically responsive and will provide feedback when receive improper input. Database needs consistency in datatype and have correctly settled constrains, as well as sustainable for future modifications by database administrator. Code needs to be free of syntax error and logical error, functions need to match the description.
#### 1.1.2 Integration testing
Integration testing will go through the connectivity between the database and UI. The test will include modifications of the data by DB administrator, then the result will be tested from UI as the observation of feedback of UI matches any changes we made.
#### 1.1.3 System testing
This is the part that examines every component of the software. We will test the feedback of UI by input some edge test cases. The performance of the software will be measured at the same time by those edge cases.
#### 1.1.4 Regression testing
There will be specific tests developed (automated if possible) to test integrity and functionality of the software after every change of code. All functions and its related (called) functions need to be tested again once there are some new changes.

### 1.2 Test Selection

- We will use both black box and white box testing.
- For black-box testing, we will test the software from functionality-wise performance. We’ll try to cover more data types as possible and make sure extreme cases will not be missed.
- For white-box testing, we will test the software from logic-wise performance. This is a code-based test, so we will make sure there’s no logical error or lines that not matching function descriptions.

### 1.3 Adequacy Criterion

- Functional coverage: We will make sure there are no missing features that are not tested. That means the test will cover every UI transition and every button click.
- Structural coverage: We will make sure every statement in the code has been executed at least once during testing.
- Code coverage rate > 80%


### 1.4 Bug Tracking

Our team will communicate effectively regarding defects. We have a shared document via Microsoft Teams to ensure bugs are handled and tracked.

### 1.5 Technology

Java code will be tested using JUnit and will be fully automated if possible. The backend database will be tested using SQL, including some logic constrains and datatype confirmation.

## 2 Test Cases

Test cases may change as the project develops.

Test Case | Test | Details | Expected | Actual | Pass/Fail 
---- | ------------- | ------------- | -------- | ------ | -----
1 | Test of cancel and return to main menu | When entering job offer, do NOT save, and click cancel button | User should be able to start over from main menu. | To be updated ||
2 | Test if the software can return an error message to user if user input a wrong datatype | When entering current/offer job descriptions, enter integer at string input box or string at a float input box | Error message should prompt user to enter correct datatype. | To be updated ||
3 | Test if the database will save incorrect input if quit | When entering current/offer job descriptions, have some boxes filled then quit the app, kill the task without saving | No information will be saved since user didn’t hit save button. | To be updated ||
4 | Test if user can edit the “current job details” | when entering details about the current job, the system can test if we have already entered a current job or not, if so, retreve the info; otherwise, leave EditText views blank  | User should be able to either enter a new current job or successfully edit current job | To be updated ||
5 | Test if the incomplete information can be saved | When entering current job/job offer, intentionally leave couple fields or one field blank, click “save” | Error message should prompt user to enter correct datatype, no data saved in the database | To be updated ||
6 | Test if the comparison weight can handle extreme values or null | When adjusting the comparison setting, if prompted to enter a number, enter a number that exceeds the limit of the setting or leave it blank | Error message should prompt user to enter a useable value | To be updated ||
7 | Test if the database keeps data after user quits the program | After a set of comparison, exit the app and kill the task directly | Examine the database, nothing should be kept there | To be updated ||
8 | Test if the app can detect abnormality when entering a comparison | When entering “Salary”, try fill with an extremely small or large number, 0.1 or 10000000 | Reminder message should prompt user to confirm if the input is correct | To be updated ||
9 | Test if the reset button works when the user clicks on the button | When clicking on the “Reset” button, the text entering views are all cleaned out | The screen should be completely blank within the EditText views without what the user just enters | To be updated ||
10 | Test if the user can add another job offer when they click on “Add Another Job Offer” button | When clicking on the “Add Another Job Offer” button, the text entering views are all cleaned out | The screen should be completely blank within the EditText views and the user can enter the details of another new job offer | To be updated ||