# Bricks
[![Build Status](https://travis-ci.org/ingogriebsch/bricks.svg?branch=development)](https://travis-ci.org/ingogriebsch/bricks)
[![Codecov Status](https://codecov.io/gh/ingogriebsch/bricks/branch/development/graph/badge.svg)](https://codecov.io/gh/ingogriebsch/bricks)
[![Codacy Status](https://api.codacy.com/project/badge/Grade/1829fc812c23499aaa0525c31e054e8d)](https://www.codacy.com/app/ingo.griebsch/bricks?utm_source=github.com&utm_medium=referral&utm_content=ingogriebsch/bricks&utm_campaign=Badge_Grade)
[![DepShield Status](https://depshield.sonatype.org/badges/ingogriebsch/bricks/depshield.svg)](https://depshield.github.io)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Bricks is a framework that allows you to create automated documentation about an application and its components. 
The framework is easy to integrate and offers maximum flexibility to document any type of application.

Frameworks like [Structurizr](https://structurizr.com/), [CodeScene](https://empear.com/) or [Arc42](https://arc42.de/) are very powerful, but cost money and / or require a lot of training. 
That's why we wanted to create an easy to use alternative that is flexible and easy to customize and expand.

Our primary goals are:

*   Build documentation based on conventions and largely generated information.
*   Storage of the generated documentation together with the component itself.
*   An easy to extend framework to document different types of applications (monolithic, microservice based).
*   Make available a visualization of the collected information in the form of a dashboard and documents that can be reused offline.

## Modules
The project consists of the following modules:

*   **bricks-model** contains the base model. It provides the abstract description of an application and its components. The definition of the model follows a concrete idea of ​​what information is needed to describe an application and its components. Nevertheless, it tries to be as flexible and expandable as possible.
*   **bricks-generate** contains all the necessary parts to generate the documentation of a component based on source code, build and meta information.
*   **bricks-maven-plugin** includes a Maven plugin that allows for the repetitive creation of component documentation during the component build process.
*   **bricks-assemble** contains all the necessary parts to collect the generated documentation from different 'places' and make it available as a data model.
*   **bricks-analyze** contains all the necessary parts to analyze the model and to calculate or aggregate certain aspects.
*   **bricks-visualize** contains all the necessary parts to visualize the model. This includes the output of the documentation as svg, ppt, pdf and other formats.
*   **bricks-dashboard** contains a Spring Boot application that processes and displays the generated components of an application and its components.
*   **bricks-example** contains examples that make certain use cases clearer.

## License
This code is open source software licensed under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0.html).
