package homework2_1.DAO;


import homework2_1.entity.Developers;
import homework2_1.entity.Projects;

import java.util.List;
import java.util.Scanner;

public class Menu {
    CompaniesDao companiesDao = new CompaniesDao();
   // CustomersDao customersDao = new CustomersDao();
    DevelopersDao developersDao = new DevelopersDao();
    ProjectDao projectDao = new ProjectDao();
    SkillDao skillDao = new SkillDao();
    Scanner scanner = new Scanner(System.in);

    private void sumSalaryAllDevelopersProject() {
        System.out.println("");
        long id = scanner.nextLong();
        Projects projects = projectDao.getProjects(id);
        List<Developers> developers = developersDao.getAllDevelopers();
        if (projects != null) {
            if (developers != null) {
                int sumSalary = developersDao.getSumDevelopersSalaryInProject(id);
                System.out.println("Сумма зарплат" + sumSalary);
            } else {
                System.out.println("Разработчиков нет");
            }

        } else {
            System.out.println("Такого проекта нет");
        }
    }

    private void getDevelopersSeparateProject() {
        System.out.println("Введите id проекта ");
        long id = scanner.nextLong();

        Projects projects = projectDao.getProjects(id);
        List<Developers> developers = developersDao.getAllDevelopers();
        if (projects != null) {
            if (developers != null) {
                System.out.println("Количество разработчиков" + developers.size() + "что работают над проектом " + id + ": ");
                for (Developers developer : developers) {
                    System.out.println(developer);
                }
            } else {
                System.out.println("Разработчиков нет");
            }

        } else {
            System.out.println("Такого проекта нет");
        }
    }

    private void allJavaDeveloper() {
        List<Developers> developers = developersDao.getJavaDeveloper();
        System.out.println("-------------------------------------------");
        if (developers != null) {
            System.out.println("Количества разработчиков JAVA" + developers.size());
            for (Developers developer : developers) {
                System.out.println(developer);
            }
        } else {
            System.out.println("Разработчиков нет");
        }

    }


    private void allMiddleDeveloper() {
        List<Developers> developers = developersDao.getAllMiddleDeveloper();
        System.out.println("-------------------------------------------");
        if (developers != null) {
            System.out.println("Количества разработчиков middle" + developers.size());
            for (Developers developer : developers) {
                System.out.println(developer);
            }
        } else {
            System.out.println("Разработчиков нет");
        }

    }


    private void show() {
        System.out.println(" \n 1 - cумма зарплат разработчиков" +
                "\n 2 - количество разработчиков которые работают над одним проектом" +
                "\n 3 - количество разработчиков java" +
                "\n 4 - Количества разработчиков middle");
        String obj = scanner.next();
        switch (obj) {
            case "1":
                sumSalaryAllDevelopersProject();
                break;
            case "2":
                getDevelopersSeparateProject();
                break;
            case "3":
                allJavaDeveloper();
                break;
            case "4":
                allMiddleDeveloper();
                break;
        }
    }

    public static void main(String[] args) {

       Menu menu = new Menu();
       menu.show();


    }


}
