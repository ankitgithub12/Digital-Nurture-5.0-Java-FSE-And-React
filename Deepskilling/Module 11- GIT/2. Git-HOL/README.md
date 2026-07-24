# Git Hands-On Lab 2: Using .gitignore

## Objectives

- Explain `.gitignore`.
- Explain how to ignore unwanted files using `.gitignore`.
- Implement `.gitignore` rules to ignore unwanted files and folders.

## Prerequisites

- Git is installed and configured.
- Notepad++ is configured as default editor (optional for this lab).
- A local Git repository and a remote repository in GitLab/GitHub.

Estimated time: 20 minutes

## What is `.gitignore`?

`.gitignore` is a configuration file used by Git to exclude files and folders from tracking.

Important notes:

- `.gitignore` affects only untracked files.
- If a file is already tracked, add it to `.gitignore` and then untrack it using:

```bash
git rm --cached <file>
```

## Lab Task

Create a `.log` file and a `log` folder in the working directory. Update `.gitignore` so that:

- All `.log` extension files are ignored.
- Any folder named `log` is ignored.

Then verify using `git status`.

## Step-by-step

1. Open terminal in this folder:

```bash
cd "D:/Java FSE And React/Deepskilling/GIT/2. Git-HOL"
```

2. Initialize repository (if needed):

```bash
git init
```

3. Create sample files/folders:

```bash
echo "application log" > app.log
mkdir log
echo "internal log file" > log/runtime.txt
echo "notes" > notes.txt
```

4. Add ignore rules in `.gitignore`:

```gitignore
*.log
**/log/
```

5. Check status:

```bash
git status
```

Expected behavior:

- `app.log` is ignored.
- `log/` folder and its contents are ignored.
- `notes.txt` appears as untracked.

6. Optional: verify ignored files explicitly:

```bash
git status --ignored
```

You should see ignored entries under `Ignored files`.

## If `.log` or `log/` was already tracked

Run:

```bash
git rm -r --cached log
git rm --cached *.log
git status
```

Then commit:

```bash
git add .gitignore notes.txt
git commit -m "Add .gitignore rules for log files and log folder"
```

## Quick summary

```gitignore
*.log
**/log/
```
