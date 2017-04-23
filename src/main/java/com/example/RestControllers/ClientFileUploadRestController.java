package com.example.RestControllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Repositories.UserRepository;

@Controller
public class ClientFileUploadRestController {
	@Autowired
	private UserRepository userRepository;
	private static final String FILES_FOLDER = "files";

	private List<String> imageTitles = new ArrayList<>();

	/*
	 * @RequestMapping("/") public String index() { return "index"; }
	 */

	@RequestMapping(value = "/api/clients/image/upload", method = RequestMethod.POST)
	public String handleFileUpload(Model model, @RequestParam("imageTitle") String imageTitle,
			@RequestParam("file") MultipartFile file, HttpServletRequest request, Authentication authentication) {
			String userloggin = authentication.getName();
			String fileName = "profileImage" + userRepository.findByEmail(userloggin).getId() + ".jpg";

		if (!file.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}

				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				file.transferTo(uploadedFile);

				imageTitles.add(imageTitle);

				return fileName;

			} catch (Exception e) {


				model.addAttribute("error", e.getClass().getName() + ":" + e.getMessage());

				return fileName;
			}
		} else {
			model.addAttribute("error", "The file is empty");
			return fileName;
		}
	}

	@RequestMapping("/api/clients/image/{fileName}")
	public void handleFileDownload(@PathVariable String fileName, HttpServletResponse res)
			throws FileNotFoundException, IOException {

		File file = new File(FILES_FOLDER, fileName + ".jpg");

		if (file.exists()) {
			res.setContentType("image/jpeg");
			res.setContentLength(new Long(file.length()).intValue());
			FileCopyUtils.copy(new FileInputStream(file), res.getOutputStream());
		} else {
			res.sendError(404, "File" + fileName + "(" + file.getAbsolutePath() + ") does not exist");
		}
	}

}