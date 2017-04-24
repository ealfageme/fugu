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

import com.example.Repositories.RestaurantRepository;


@Controller
public class RestaurantFileUploadRestController {

	@Autowired
	private RestaurantRepository restaurantRepository;
	private static final String FILES_FOLDER = "files";

	private List<String> imageTitles = new ArrayList<>();

	@RequestMapping(value = "/api/restaurants/image/upload", method = RequestMethod.POST)
	public String handleFileUpload(Model model,
			@RequestParam("file") MultipartFile file, HttpServletRequest request, Authentication authentication) {

		String restaurantloggin = authentication.getName();
		String fileName = "profileImageRestaurant" + restaurantRepository.findByEmail(restaurantloggin).getId()
				+ ".jpg";
		if (!file.isEmpty()) {
			try {
				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}
				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				file.transferTo(uploadedFile);
				return fileName;

			} catch (Exception e) {
				return fileName;
			}
		} else {
			return fileName;
		}
	}

	@RequestMapping("/api/restaurants/image/{fileName}")
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

	// MENUS

	@RequestMapping(value = "/api/restaurants/menu/image/upload", method = RequestMethod.POST)
	public String handleFileUploadMenu(Model model, @RequestParam("imageTitle") String imageTitle,
			@RequestParam("file") MultipartFile file, HttpServletRequest request, Authentication authentication) {

		String restaurantloggin = authentication.getName();
		String fileMenuName = "menuImageRestaurant"
				+restaurantRepository.findByEmail(restaurantloggin).getId()+ (restaurantRepository.findByEmail(restaurantloggin).getMenus().size() + 1) + ".jpg";
		if (!file.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}

				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileMenuName);
				file.transferTo(uploadedFile);

				imageTitles.add(imageTitle);


				return fileMenuName;

			} catch (Exception e) {
				return fileMenuName;
			}
		} else {
			return fileMenuName;
		}
	}

	@RequestMapping("/api/restaurants/menu/image/{fileMenuName}")
	public void handleFileDownloadMenu(@PathVariable String fileMenuName, HttpServletResponse res)
			throws FileNotFoundException, IOException {

		File file = new File(FILES_FOLDER, fileMenuName + ".jpg");

		if (file.exists()) {
			res.setContentType("image/jpeg");
			res.setContentLength(new Long(file.length()).intValue());
			FileCopyUtils.copy(new FileInputStream(file), res.getOutputStream());
		} else {
			res.sendError(404, "File" + fileMenuName + "(" + file.getAbsolutePath() + ") does not exist");
		}
	}
}