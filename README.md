2048v
==============

[![Build Status](https://travis-ci.org/nineunderground/2048v.svg?branch=master)](https://travis-ci.org/nineunderground/2048v)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/43bccf4fe2fd46fca7af6a2468490ab6)](https://www.codacy.com/app/nineunderground/2048v?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=nineunderground/2048v&amp;utm_campaign=Badge_Grade)
[![Code Climate](https://codeclimate.com/github/nineunderground/2048v/badges/gpa.svg)](https://codeclimate.com/github/nineunderground/2048v)
[![Coverage Status](https://coveralls.io/repos/github/nineunderground/2048v/badge.svg?branch=master)](https://coveralls.io/github/nineunderground/2048v?branch=master)

[You can try here](https://v2048.herokuapp.com)

Simple Vaadin application that only requires a Servlet 3.0 container to run. It has Jetty server embedded into war file

Workflow
========

Basic Vaadin web classic 2048 game with Vaadin Touchkit included.

Views
-------------------------

|   Game on     |   Game over   |
| ------------- | ------------- |
|  ![Upload logo](docs/screenshots/screenshots_1.PNG "Screenshot 1") | ![Setup logo](docs/screenshots/screenshots_2.PNG "Screenshot 2")  |

LICENSE
========
[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](http://www.gnu.org/licenses/gpl-3.0)

Running project locally
-------------------------

From Vaadin template doc...
To compile the entire project, run "mvn install".
To run the application, run "mvn jetty:run" and open http://localhost:8080/ .
