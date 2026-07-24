# Git Hands-On Lab (Git HOL)

## Objectives

- Get familiar with core Git commands: `git init`, `git status`, `git add`, `git commit`, `git pull`, and `git push`.
- Configure Git on your machine.
- Set `notepad++.exe` as the default Git editor.
- Add and commit a file to a repository.

## Prerequisites

- Install Git Bash on your machine.
- Create an account on GitLab (or GitHub) using a personal email.

Estimated time: 30 minutes

## Step 1: Configure Git on your machine

1. Verify Git installation:

```bash
git --version
```

2. Configure username and email (global):

```bash
git config --global user.name "Your Name"
git config --global user.email "you@example.com"
```

3. Verify configuration:

```bash
git config --global --list
```

## Step 2: Set Notepad++ as default Git editor

1. Check if Notepad++ is available from Git Bash:

```bash
notepad++
```

If command is not recognized, add Notepad++ install path to your Windows `Path` environment variable.

Common path examples:

- `C:\Program Files\Notepad++\`
- `C:\Program Files (x86)\Notepad++\`

2. Close and reopen Git Bash, then verify again:

```bash
notepad++
```

3. Create alias `npp` for convenience:

```bash
git config --global alias.npp '!"C:/Program Files/Notepad++/notepad++.exe"'
```

Note: If installed under `Program Files (x86)`, update the path accordingly.

4. Set Notepad++ as default Git editor:

```bash
git config --global core.editor "'C:/Program Files/Notepad++/notepad++.exe' -multiInst -notabbar -nosession -noPlugin"
```

5. Verify editor configuration:

```bash
git var GIT_EDITOR
git config --global -e
```

`-e` opens the global Git config in your configured editor.

## Step 3: Add a file and commit to local repository

1. Create project directory and initialize Git repository:

```bash
mkdir GitDemo
cd GitDemo
git init
```

2. Verify repository files:

```bash
ls -la
```

3. Create `welcome.txt` and add content:

```bash
echo "Welcome to Git HOL" > welcome.txt
```

4. Verify file creation:

```bash
ls
```

5. Verify file content:

```bash
cat welcome.txt
```

6. Check repository status:

```bash
git status
```

7. Stage file for commit:

```bash
git add welcome.txt
```

8. Commit (this may open Notepad++ if message is not passed inline):

```bash
git commit -m "Add welcome.txt"
```

Alternative (opens editor):

```bash
git commit
```

9. Verify clean working tree:

```bash
git status
```

## Step 4: Connect remote and sync

1. Create remote repository `GitDemo` on GitLab (or GitHub).

2. Add remote origin:

```bash
git remote add origin <REMOTE_URL>
```

3. Pull remote changes first (if remote has initial commit/README):

```bash
git pull origin master --allow-unrelated-histories
```

If your default branch is `main`, use `main` instead of `master`.

4. Push local commits to remote:

```bash
git push -u origin master
```

If your branch is `main`:

```bash
git push -u origin main
```

## Quick command summary

```bash
git --version
git config --global user.name "Your Name"
git config --global user.email "you@example.com"
git config --global --list
git config --global core.editor "'C:/Program Files/Notepad++/notepad++.exe' -multiInst -notabbar -nosession -noPlugin"
mkdir GitDemo
cd GitDemo
git init
echo "Welcome to Git HOL" > welcome.txt
git add welcome.txt
git commit -m "Add welcome.txt"
git remote add origin <REMOTE_URL>
git pull origin master --allow-unrelated-histories
git push -u origin master
```
