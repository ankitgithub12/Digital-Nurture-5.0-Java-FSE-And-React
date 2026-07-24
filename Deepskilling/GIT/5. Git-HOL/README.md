# Git Clean Up and Push Back to Remote Hands-on (Git-HOL 5)

## Objectives
- Explain how to clean up local changes and push them back to the remote Git repository.
- Verify the branch state before syncing with remote.
- Pull the latest changes from remote and push pending work back to origin.

## Prerequisites
- Hands-on ID: `Git-T03-HOL_002`
- Git Bash installed.
- A free GitHub account for remote access.
- Do not use cognizant credentials to log in to GitHub.

Estimated time: **10 minutes**

## Important Note
If your repository uses `main` instead of `master`, replace `master` with `main` in the commands below.

## Lab Steps
Run these commands from this folder:

### 1. Verify if `master` is in a clean state
```bash
git status
```

### 2. List all available branches
```bash
git branch
```

### 3. Pull the remote Git repository into `master`
```bash
git checkout master
git pull origin master
```

### 4. Push the pending changes from `Git-T03-HOL_002` to the remote repository
```bash
git add .
git commit -m "Clean up and push changes for Git-T03-HOL_002"
git push origin master
```

### 5. Observe the changes in the remote repository
- Refresh the GitHub repository page and verify that the latest commit is visible.
- Confirm that the files and commit message match the changes made in this lab.

## How the Workflow Works
The safe sequence for syncing local work back to remote is:

1. Check the current branch and working tree state.
2. Make sure your local branch is up to date with the remote branch.
3. Stage and commit the required changes.
4. Push the commit to the remote repository.
5. Verify that the remote repository shows the updated history.

## Notes From This Environment
- The repository default branch is `main` in this workspace, so use `main` instead of `master` if your Git setup is already on `main`.