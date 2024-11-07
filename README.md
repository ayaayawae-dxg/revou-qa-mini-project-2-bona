# Mini Project + Milestone Assignment: Advanced Automated Testing on API, WEB, and Mobile

## Description
To design and implement advanced automated tests for API, web, and mobile applications, ensuring high coverage, maintainability, and scalability.

[Summary Report](https://docs.google.com/document/d/1OqOSo2pzETA9_ppxr3WFdMTyl9x25XLQkVNeF-uq5Tw/edit?usp=sharing)

<!-- Explain the docker compose file -->
## Docker Compose Configuration

The `docker-compose.yml` file sets up a Selenium Grid infrastructure with the following services:

### Chrome Node
- Uses the latest Selenium Chrome node image
- Configured with 2GB shared memory
- Connects to Selenium Hub for test distribution
- Exposes VNC port 7900 for viewing test execution
- Environment variables configure connection to Hub

### Selenium Hub
- Uses Selenium Hub 4.1.3 image
- Acts as the central point managing test distribution
- Exposes ports:
  - 4442: Event bus publish port
  - 4443: Event bus subscribe port 
  - 4444: Main Selenium Grid port
- Named container "selenium-hub"

### Jenkins
- Uses latest LTS Jenkins image
- Exposes port 8080 for web interface
- Can be used for CI/CD pipeline automation

### Network
- Creates "selenium-grid" bridge network
- Enables communication between containers
- All services are connected to this network

This setup allows for:
- Parallel test execution across Chrome nodes
- Central test management via Selenium Hub
- CI/CD integration through Jenkins
- Visual monitoring of tests via VNC

