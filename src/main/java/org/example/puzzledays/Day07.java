package org.example.puzzledays;

import org.example.utils.PuzzleDay;

import java.util.ArrayList;
import java.util.List;

record Directory(String name, List<Directory> dirs, List<String[]> files, Directory parentDirectory) {
}

public class Day07 extends PuzzleDay {
    Directory root = new Directory("/", new ArrayList<>(), new ArrayList<>(), null);
    long partOneTotalSize = 0;
    Object[] partTwoFoundDir = new Object[]{null, 70000000L};

    public Day07() {
        super(true, true, false);
    }

    @Override
    public long getSolutionPartOne(ArrayList<String> input) {
        parseInput(input);
        checkAllDirectoryNodesOfTreeForSize(root);

        return partOneTotalSize;
    }

    @Override
    public long getSolutionPartTwo(ArrayList<String> input) {
        final long DISKSPACE = 70000000;
        final long SPACE_NEEDED = 30000000;

        long currentlyInUse = getDirectorySize(root);
        long minAmountToDelete = SPACE_NEEDED - (DISKSPACE - currentlyInUse);

        findBestDirectoryToDelete(root, minAmountToDelete);
        return (long) partTwoFoundDir[1];
    }

    private void findBestDirectoryToDelete(Directory dir, long targetSize) {
        long size = getDirectorySize(dir);
        if (size > targetSize && size < (Long) partTwoFoundDir[1]) {
            partTwoFoundDir[0] = dir;
            partTwoFoundDir[1] = size;
        }
        for (Directory subDir : dir.dirs()) {
            findBestDirectoryToDelete(subDir, targetSize);
        }
    }

    private void checkAllDirectoryNodesOfTreeForSize(Directory dir) {
        long size = getDirectorySize(dir);
        if (size < 100000) {
            partOneTotalSize += size;
        }
        for (Directory subDir : dir.dirs()) {
            checkAllDirectoryNodesOfTreeForSize(subDir);
        }
    }

    private long getDirectorySize(Directory dir) {
        long size = 0;

        for (String[] file : dir.files()) {
            size += Long.parseLong(file[0]);
        }
        for (Directory subDir : dir.dirs()) {
            size += getDirectorySize(subDir);
        }

        return size;
    }

    private void parseInput(ArrayList<String> input) {
        Directory currentActiveDirectory = root;

        for (int i = 1; i < input.size(); i++) {
            String currLine = input.get(i);

            if (currLine.startsWith("$ cd ")) {
                if (currLine.equals("$ cd ..")) {
                    currentActiveDirectory = currentActiveDirectory.parentDirectory();
                } else {
                    String folderName = currLine.split(" ")[2];
                    currentActiveDirectory = getFolderInDirectory(folderName, currentActiveDirectory);
                }
            }

            if (currLine.equals("$ ls")) {
                // start sub loop to parse contents of folder
                while (!input.get(i + 1).startsWith("$")) {
                    i++;
                    String newLine = input.get(i);

                    if (newLine.startsWith("dir")) {
                        Directory newDir = new Directory(newLine.split(" ")[1], new ArrayList<>(), new ArrayList<>(), currentActiveDirectory);
                        currentActiveDirectory.dirs().add(newDir);
                    } else {
                        currentActiveDirectory.files().add(newLine.split(" "));
                    }

                    if (i == input.size() - 1) {
                        break;
                    }
                } // end of sub loop
            }

        } // end for

    }

    private Directory getFolderInDirectory(String name, Directory dir) {
        List<Directory> found = dir.dirs().stream().filter(x -> x.name().equals(name)).toList();
        return found.get(0);
    }

}
