package com.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFile {

    private static final int BUFFER_SIZE = 1024;
    private static final int DEFAULT_LIMIT = 10000;
    private List<String> text = new ArrayList<>();
    private MetaData metaData;
    private File file;

    /**
     * Initializing object by concrete file instance
     */
    public TextFile(File file) {
        this.file = file;
        metaData = new MetaData(file);
    }

    /**
     * Initializing object by concrete file instance
     *
     * @param fileName Name of file which is placed inside resources/files folder
     */
    public TextFile(String directoryName, String fileName) {
        this.file = directoryName.equals("") ? getFile(fileName) : getFile(directoryName, fileName);
        metaData = new MetaData(file);
    }

    /**
     * Fill text attribute according to input parameters
     */
    public void fill(String q, int length, int limit) throws Exception {
        if (q.equals("")) {
            text.add(readFile(limit > 0 ? limit : DEFAULT_LIMIT));
        } else {
            text.addAll(readFile(q, length, limit > 0 ? limit : DEFAULT_LIMIT));
        }
    }

    /**
     * Querying file content. Return list of all occurrences. *{word}*
     *
     * @param q Query word
     * @param length Maximum word length
     * @param limit Total limit of result length
     * @return List of occurrences (String)
     */
    private List<String> readFile(String q, int length, int limit) throws Exception {
        List<String> resultList = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\S*(" + q + ")\\S*");

        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            int totalLength = 0;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    int start = matcher.start(0);
                    int end = matcher.end(0);
                    int wordLength = end - start;

                    if (end > start && (length < 0 || wordLength <= length)) {
                        totalLength += wordLength;
                        if (totalLength <= limit) {
                            resultList.add(line.substring(start, end));
                        }
                    }
                }
            }
        } finally {
            bufferedReader.close();
        }
        return resultList;
    }

    /**
     * Reading file up to limit
     *
     * @param limit Maximum result size
     * @return String result
     */
    private String readFile(int limit) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            int totalLength = 0;
            while ((line = bufferedReader.readLine()) != null) {
                int lineLength = line.length();
                if (totalLength + lineLength <= limit) {
                    totalLength += lineLength;
                    stringBuilder.append(line);
                } else {
                    stringBuilder.append(line.substring(0, (limit - totalLength)));
                    break;
                }
            }
        } finally {
            bufferedReader.close();
        }
        return stringBuilder.toString();
    }

    /**
     * Get file inside /resources/files folder
     *
     * @return FIle
     */
    public File getFile(String fileName) {
        return getFile("files", fileName);
    }

    /**
     * Use for receive file inside other directory
     *
     * @param directory Specify folder path which contains file
     * @param fileName Name of file
     * @return File object
     */
    public File getFile(String directory, String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(directory + "/" + fileName).getFile());
        return file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setFile(String fileName) {
        this.file = getFile(fileName);
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}
