# Requirements
## 1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet). 
When the app starts, it only show the four buttons as mentioned above in the UI. No extra operations will be needed.

## 2. When choosing to enter current job details, a user will:
> a. Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consist of:
> - Title
> - Company
> - Location (entered as city and state)
> - Cost of living in the location (expressed as an index)
> - Yearly salary 
> - Yearly bonus 
> - Gym Membership
> - Leave time (in days)
> - 401K Match (0-20 inclusive, expressed as a whole percent)
> - Pet Insurance ($0 to $5,000 inclusive annually)

When the `enter current job details` button is clicked, the app will render a new UI with the properties mentioned above. In the meantime, the app will call `GetCurrentJob` function to check if user has already entered a current job and saved in database. If not, then the values will be blank, otherwise, the values will be filled with the values in database.

> b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

When the user save the job, the `SaveJob` function will be called. If there is an Id passed to the function, the function will update the job with the Id. Otherwise, the function will create a new job and save it to database. After the job is saved, the app will return to the main menu. For cancel and exit, the app will return to the main menu and these are pure UI works.

## 3. When choosing to enter job offers, a user will:
> a. Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.

Pure UI works.

> b. Be able to either save the job offer details or cancel.

Same as saving in `enter current job details`.

> c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

Pure UI works.

## 4. When adjusting the comparison settings, the user can assign integer weights to:
- Yearly salary
- Yearly bonus
- Gym Membership
- Leave time
- 401K Match
- Pet Insurance

If no weights are assigned, all factors are considered equal.

UI will show the current ConparisonSetting with the default values. If user wants to adjust the values, the `AdjustComparisonSetting` function will be called to modify the setting.

## 5. When choosing to compare job offers, a user will:
> a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.

First, the `GetAllJobs` function will be called to retrieve all jobs in the database back. Next, for each job, `CalculateJobScore` function will be called to calculate the score based on the ComparisonSetting. At last, the jobs will be sorted by the score and rendered in the UI.

> b. Select two jobs to compare and trigger the comparison.

Pure UI works.

> c. Be shown a table comparing the two jobs, displaying, for each job:
> - Title
> - Company
> - Location 
> - Yearly salary adjusted for cost of living
> - Yearly bonus adjusted for cost of living
> - Gym Membership
> - Leave time
> - 401K 
> - Pet Insurance

`GetJobDetails` function will be called for each job to retrieve all the information of the job. Then the UI will be rendered based on the information.

> d. Be offered to perform another comparison or go back to the main menu.

Pure UI works.

## 6. When ranking jobs, a job’s score is computed as the weighted average of:
AYS + AYB + GYM + (LT * AYS / 260) + (AYS * 401K / 100 ) + PET

where:
AYS = yearly salary adjusted for cost of living
AYB = yearly bonus adjusted for cost of living
GYM = Gym Membership Allowance ($0 to $500 annually)
LT = leave time
401K = % 401K Match 
PET = Pet Insurance 

For example, if the weights are 2 for the yearly salary, 2 for the yearly bonus, 2 for Pet Insurance, and 1 for all other factors, the score would be computed as:


2/9 * AYS + 2/9 * AYB + 1/9 * GYM + 1/9 * (LT * AYS / 260) + 1/9 * (AYS * 401K / 100) + 2/9 * PET


Inner calculation logic for `CalculateJobScore` function.

## 7. The user interface must be intuitive and responsive.

Pure UI works.

## 8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

Assuming only one user is using the system locally.