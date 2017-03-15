package com.example.Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	private static final String FILES_FOLDER = "files";

	private List<String> imageTitles = new ArrayList<>();

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/private-client/", method = RequestMethod.POST)
	public String handleFileUpload(Model model, HttpServletRequest request,Authentication authentication,
			@RequestParam("file") MultipartFile file,HttpServletResponse res) throws FileNotFoundException, IOException {

		String fileName = authentication.getName()+ ".jpg";

		if (!file.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}

				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				file.transferTo(uploadedFile);

				imageTitles.add(authentication.getName());
				
			
				
			

			} catch (Exception e) {
				
				model.addAttribute("fileName",fileName);
				model.addAttribute("error",
						e.getClass().getName() + ":" + e.getMessage());
				
				return "/private-client/";
			}
		} else {
			
			model.addAttribute("error",	"The file is empty");
			
			return "/private-client/";
		}
		File filea = new File(FILES_FOLDER,authentication.getName()+".jpg");

		if (filea.exists()) {
			res.setContentType("image/jpeg");
			res.setContentLength(new Long(filea.length()).intValue());
			FileCopyUtils.copy(new FileInputStream(filea), res.getOutputStream());
		} else {
			res.sendError(404, "File" + authentication.getName() + "(" + filea.getAbsolutePath()+ ") does not exist");
		}return "/private-client/";
	}

}