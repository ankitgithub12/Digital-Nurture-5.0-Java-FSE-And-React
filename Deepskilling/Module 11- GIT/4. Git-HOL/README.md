# Git Conflict Resolution Hands-on (Git-HOL 4)

## Objectives
- Explain how to resolve a conflict during merge.
- Practice a branch-to-trunk merge where both sides modify the same file.
- Use Git commands to inspect differences, resolve conflicts, and finish the merge.

## Prerequisites
- Hands-on ID: `Git-T03-HOL_001`
- Git Bash installed.
- A free GitHub account if remote collaboration is required.
- Do not use cognizant credentials to log in to GitHub.

Estimated time: **30 minutes**

## Important Note
If your repository uses `main` instead of `master`, replace `master` with `main` in the commands below.

## Lab Steps
Run these commands from this folder:

### 1. Verify that `master` is in a clean state
```bash
git status
```

### 2. Create a branch named `GitWork` and add `hello.xml`
```bash
git checkout -b GitWork
echo "<hello>Branch version</hello>" > hello.xml
git add hello.xml
git commit -m "Add hello.xml in GitWork"
```

### 3. Update `hello.xml` and observe the status
```bash
echo "<hello>Updated branch content</hello>" > hello.xml
git status
```

### 4. Commit the changes in the branch
```bash
git add hello.xml
git commit -m "Update hello.xml in GitWork"
```

### 5. Switch to `master`
```bash
git checkout master
```

### 6. Add another `hello.xml` file on `master` with different content
```bash
echo "<hello>Master version</hello>" > hello.xml
git add hello.xml
git commit -m "Add hello.xml on master"
```

### 7. Observe the history graph
```bash
git log --oneline --graph --decorate --all
```

### 8. Check the differences with Git diff
```bash
git diff master GitWork
```

### 9. Use P4Merge for a visual comparison
```bash
git difftool --tool=p4merge --no-prompt master GitWork
```

### 10. Merge the branch into `master`
```bash
git merge GitWork
```

### 11. Observe the merge markers
If Git reports a conflict, open `hello.xml` and look for markers like these:
```text
<<<<<<< HEAD
master content
=======
branch content
>>>>>>> GitWork
```

### 12. Resolve the conflict using a 3-way merge approach
Choose the final content you want to keep, remove the conflict markers, and save the file. A simple resolution can look like this:
```xml
<hello>Master and branch content resolved</hello>
```

### 13. Mark the conflict as resolved and commit it
```bash
git add hello.xml
git commit -m "Resolve merge conflict in hello.xml"
```

### 14. Observe the status and add backup files to `.gitignore`
If merge tools create a backup file, add it to `.gitignore`:
```bash
echo *.orig >> .gitignore
git add .gitignore
git commit -m "Add backup files to gitignore"
```

### 15. List all available branches
```bash
git branch
```

### 16. Delete the merged branch
```bash
git branch -d GitWork
```

### 17. Observe the final history graph
```bash
git log --oneline --graph --decorate
```

## How Conflict Resolution Works
A merge conflict happens when Git cannot automatically combine changes because both branches changed the same file or the same lines in different ways. The safe workflow is:

1. Run the merge command.
2. Open the conflicted file.
3. Compare the `HEAD` version and the incoming branch version.
4. Edit the file to keep the correct final content.
5. Remove all conflict markers.
6. Run `git add` on the resolved file.
7. Complete the merge with a commit.

## Notes From This Environment
- `p4merge` was not found in PATH in this environment, so the visual diff step may need a locally configured install.
- If your repository is already on `main`, use `main` instead of `master` throughout the lab.
