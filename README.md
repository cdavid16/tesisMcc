This project holds the code related with Master's degree final work.

The intention of this is to be able to fetch PRs from github and their comments using public rest API, they will be exported as JSON files in the out folder.
In order to make it constant FILE_PATH in PullRequest class must be updated since it's hardcoded for the time being.

Below variables must be set using your own github token:
- AUTH_KEY
- GH_TOKEN


You must set proper fine grained access while generating your github token, is important to use it else the APIs are very limited in access wise.
