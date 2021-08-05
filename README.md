# Jenkins-Groovy-task
## Task 0 - setup own development environment:
- IDE (we use IntelliJ IDEA / Visual Studio Code);
- Remote git repository (e.g private repo in git.epam.com);
- Local Jenkins (I run Jenkins in docker on VirtualBox).
## Task 1:
- Create simple job, e.g. 'echo hello world' in shell;
- Execute it couple of times;
- Explore job and its builds using system groovy;
- Execute job via script console in Jenkins.
## Task 2:
- Create one pipeline and two freestyle jobs;
- Pipeline should trigger freestyle jobs;
- Statuses of downstream builds should be posted into description of pipeline build.
## Task 3:
- Create Pipeline Job which use step from shared library (function \ custom step);
- Create step which use third-party Java libraries;
- Create a class in shared library.
## Task 4:
- Create Job DSL to generate 10 jobs (diff params) from template;
- Generate Jobs via 'seed' job;
- Generate Jobs via script console in Jenkins.
## Task 5:
- Create Dockerfile and resources needed to auto configure your user in Jenkins in case of fresh Jenkins installation;
- Add needed resources to generate 'seed' job in case of fresh Jenkins installation, which you can trigger manually to generate all other jobs; 
- Modify Dockerfile and resources to have the same jenkins version and plugins as on current ctech and ensure that fresh installation including generation of all jobs are still working;
- Autoconfigured Jenkins should include Job DSL which creates: 10 freestylejobs, 1 pipeline job (Triggered 2 freestyle jobs). After Jenkins autoconfiguration, all created jobs must be executed without manual approval.
