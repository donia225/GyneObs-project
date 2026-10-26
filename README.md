
# Git Workflow Guidelines

This document explains how to use Git and GitHub to collaborate on the GyneObs project.

## 1. Clone the Repository

Clone the project to your local machine:

```bash
git clone <repository-url>
```

## 2. Create a New Branch

Each feature or bug fix must be developed in a separate branch.

Before creating a new branch, make sure your local `main` branch is up to date:

```bash
git switch main
git pull origin main
```

### Create a New Branch

```bash
git branch feature/patient-management
```

### Switch to the New Branch

```bash
git switch feature/patient-management
```

### Branch Naming Conventions

- `feature/patient-management` — New feature
- `fix/patient-search` — Bug fix
- `refactor/patient-service` — Code refactoring

## 3. Commit Messages

After making changes, stage your files:

```bash
git add .
```

### Commit Message Conventions

**New feature:**

```bash
git commit -m "feat: add patient management"
```

**Bug fix:**

```bash
git commit -m "fix: resolve patient search error"
```

**Code refactoring:**

```bash
git commit -m "refactor: simplify patient service"
```

**Documentation update:**

```bash
git commit -m "docs: update README"
```

## 4. Push Changes to GitHub

Push your branch to GitHub for the first time:

```bash
git push -u origin feature/patient-management
```

For subsequent pushes:

```bash
git push
```

## 5. Create a Pull Request

Once your feature is completed:

1. Open the GitHub repository.
2. Select your feature branch.
3. Click **Compare & pull request**.
4. Describe your changes.
5. Request a review from your teammate.
6. Merge the Pull Request into `main` after review and testing.

## 6. Important Rules

- Never develop directly on `main`.
- Create a separate branch for each feature or bug fix.
- Write clear and meaningful commit messages.
- Test your changes before creating a Pull Request.
- Review your teammate's code before merging.
- Never commit passwords, API keys, or sensitive data.
