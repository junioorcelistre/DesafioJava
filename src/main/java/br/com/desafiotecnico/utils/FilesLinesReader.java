package br.com.desafiotecnico.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilesLinesReader {

	private final static Logger logger = LoggerFactory.getLogger(FilesLinesReader.class);

	public static List<String> readAllLineFromAllFiles(final String path, final String extension) {
		final List<String> lines = new ArrayList<>();
		try (final Stream<Path> pathStream = Files.walk(Paths.get(path), FileVisitOption. FOLLOW_LINKS)) {
			pathStream.filter((p) -> !p.toFile().isDirectory() && p.toFile().getAbsolutePath().endsWith(extension))
					.forEach(p -> listLinesFiles(p, lines));
		} catch (final IOException e) {
			logger.error(e.getMessage(), e);
		}
		return lines;
	}

	private static void listLinesFiles(final Path file, final List<String> lines) {
		try (Stream<String> stream = Files.lines(file, Charset.defaultCharset())) {
			stream.map(String::trim).filter(s -> !s.isEmpty()).forEach(lines::add);
		} catch (final IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
