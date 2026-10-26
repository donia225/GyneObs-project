Git Workflow Guidelines :
This document explains how to use Git and Github to collaborate on the GyneObs project.

1) Clone the repository :
# Clone the project to your local machine
git clone <repository-url>

2) Create a new branch :
Each feature or bug fix must be developed in a separate branch.
Before creating a branch, make sure your local main branch is up to date:
-> git switch main
-> git pull origin main
   
# Create a new branch
git branch feature/patient-management
# Switch to the new branch when ready
git switch feature/patient-management

Branch naming conventions:
feature/patient-management — New feature
fix/patient-search — Bug fix
refactor/patient-service — Code refactoring

3) Commit messages :
# After making changes, stage your files:
git add .

git commit -m "add: new feature patient" -> new fonctionnality
git commit -m "fix: add patient" -> fixer une fonctionnalité ou modifier quelque chose
git commit -m "refactor: ..." 
