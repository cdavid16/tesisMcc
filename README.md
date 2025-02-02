This project holds the code related with Master's degree final work.

The intention of this is to be able to fetch PRs from github and their comments using public rest API, they will be exported as JSON files in the out folder.
In order to make it constant FILE_PATH in PullRequest class must be updated since it's hardcoded for the time being.

Below variables must be set using your own github token:
- AUTH_KEY
- GH_TOKEN


You must set proper fine-grained access while generating your github token, is important to use it else the APIs are very limited in access wise.

Next are the proposed classes for PR Comments:
- **Best Practice**: This comment is related when you don't follow the best engineering practices.
- **Business Domain**: This is when you are not following business related logic.
- **Suggestion**: This is only a suggested change, but not mandatory.
- **Documentation**: This change is related when you are not following the documentation.
- **Question**: This is related with a question, not a change per se.
- **Code Style**: This type of comment is related with the code style within the team.