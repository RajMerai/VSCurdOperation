package com.xadmin.SpringBootCurd;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.xadmin.SpringBootCurd.bean.Subject;
import com.xadmin.SpringBootCurd.service.SubjectService;

@SpringBootApplication
public class SpringBootCurdOperApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootCurdOperApplication.class, args);
		SubjectService subjectService = context.getBean(SubjectService.class);

		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		while (!exit) {
			System.out.println("Choose an operation: ");
			System.out.println("1. Add Subject");
			System.out.println("2. Update Subject");
			System.out.println("3. Delete Subject");
			System.out.println("4. Get All Subjects");
			System.out.println("5. Exit");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
				case 1:
					System.out.println("Enter Subject ID: ");
					String id = scanner.nextLine();
					System.out.println("Enter Subject Name: ");
					String name = scanner.nextLine();
					subjectService.addSubject(new Subject(id, name));
					System.out.println("Subject added successfully.");
					break;
				case 2:
					System.out.println("Enter Subject ID to update: ");
					id = scanner.nextLine();
					System.out.println("Enter new Subject Name: ");
					name = scanner.nextLine();
					subjectService.updateSubject(id, new Subject(id, name));
					System.out.println("Subject updated successfully.");
					break;
				case 3:
					System.out.println("Enter Subject ID to delete: ");
					id = scanner.nextLine();
					subjectService.deleteSubject(id);
					System.out.println("Subject deleted successfully.");
					break;
				case 4:
					System.out.println("List of all subjects: ");
					subjectService.getAllSubject().forEach(System.out::println);
					break;
				case 5:
					exit = true;
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
		scanner.close();
		context.close();
	}
}