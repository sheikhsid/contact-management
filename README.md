<h1>Contact Management</h1>

<p>Contact Management is a web application built using Java, Maven, and Spring Boot. It provides a RESTful API for managing contacts and a simple HTML frontend for interacting with the API. Docker is utilized to containerize the application, enabling easy deployment in any environment.</p>

| Badge                                                     | URL                                                                                           |
|-----------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| Sonarcloud Quality                                       | [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=sheikhsid_contact_management&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=sheikhsid_contact_management) |
| Sonar Cloud Coverage and Ratings                         | [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=sheikhsid_contact_management&metric=coverage)](https://sonarcloud.io/summary/new_code?id=sheikhsid_contact_management) |
| Reliability Rating                                       | [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=sheikhsid_contact_management&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=sheikhsid_contact_management) |
| Duplicated Lines (%)                                     | [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=sheikhsid_contact_management&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=sheikhsid_contact_management) |
| Vulnerabilities                                          | [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=sheikhsid_contact_management&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=sheikhsid_contact_management) |
| Bugs                                                      | [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=sheikhsid_contact_management&metric=bugs)](https://sonarcloud.io/summary/new_code?id=sheikhsid_contact_management) |
| Security Rating                                          | [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=sheikhsid_contact_management&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=sheikhsid_contact_management) |
| Maintainability Rating                                   | [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=sheikhsid_contact_management&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=sheikhsid_contact_management) |
| Code Smells                                              | [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=sheikhsid_contact_management&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=sheikhsid_contact_management) |
| Coverage Status                                          | [![![Coverage Status](https://coveralls.io/repos/github/sheikhsid/contact-management/badge.svg?branch=master)](https://coveralls.io/github/sheikhsid/contact-management?branch=master) |
| Java CI with Maven                                        | [![Java CI with Maven](https://github.com/sheikhsid/contact-management/actions/workflows/maven.yml/badge.svg)](https://github.com/sheikhsid/contact-management/actions/workflows/maven.yml) |


<h2>Getting Started</h2>

<p>To run the application, follow the steps below:</p>

<ol>
  <li>Clone the repository: <code>git clone https://github.com/sheikhsid/contact-management.git</code></li>
  <li>Import the project into Eclipse:
    <ul>
      <li>Open Eclipse and select <strong>File -> Import</strong>.</li>
      <li>Choose <strong>Maven -> Existing Maven Projects</strong> and click <strong>Next</strong>.</li>
      <li>Browse to the directory where you cloned the repository and select the project.</li>
      <li>Click <strong>Finish</strong> to import the project into Eclipse.</li>
    </ul>
  </li>
  <li>In Eclipse, right-click on the project and select <strong>Run As -> Maven Install</strong> to build the project and download the required dependencies.</li>
  <li>Once the Maven build is successful, you can proceed with running the application using Docker.</li>
  <li>Build the Docker image: <code>docker build -t contact-management .</code></li>
  <li>Run the Docker container: <code>docker run -p 8080:8080 contact-management</code></li>
  <li>The application will now be accessible at <a href="http://localhost:8080">http://localhost:8080</a>.</li>
</ol>

<h2>API Endpoints</h2>

<p>The REST API provides the following endpoints:</p>

<ul>
  <li><strong>GET localhost:8080/contacts</strong>: Retrieves a list of all contacts.</li>
  <li><strong>GET localhost:8080/contacts/{id}</strong>: Retrieves a specific contact by ID.</li>
  <li><strong>POST localhost:8080/contacts</strong>: Creates a new contact.</li>
  <li><strong>DELETE localhost:8080/contacts/{id}</strong>: Deletes a contact.</li>
</ul>

<p>Please refer to the API documentation included in the project for detailed information on the request and response formats.</p>

<h2>HTML Frontend</h2>

<p>The HTML frontend allows users to interact with the API using a user-friendly interface. It is accessible at <a href="http://localhost:8080">http://localhost:8080</a>.</p>

<h2>Project Structure</h2>

<p>The project follows a standard Maven structure. Key files and directories include:</p>

<ul>
    <li><strong>src/main/java</strong>: Contains the Java source code.</li>
    <li><strong>src/main/resources</strong>: Contains application properties and static files.</li>
    <li><strong>src/test/java</strong>: Contains unit tests, integration tests, and end-to-end tests.</li>
    <li><strong>src/test/resources</strong>: Contains test properties and test scenarios like <code>contact_add.feature</code> for end-to-end testing.</li>
    <li><strong>pom.xml</strong>: Contains Maven configuration and project dependencies.</li>
</ul>
