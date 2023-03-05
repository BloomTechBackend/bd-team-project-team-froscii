# Team Froscii Design Document

## Instructions

*Save a copy of this template for your team in the same folder that contains
this template.*

*Replace italicized text (including this text!) with details of the design you
are proposing for your team project. (Your replacement text shouldn't be in
italics)*

*You should take a look at the example design document in the same folder as
this template for more guidance on the types of information to capture, and the
level of detail to aim for.*

## *Froscii* Design

## 1. Problem Statement

There is an issue for people who have very little skill with drawing digitally 
with a mouse, trackpad, or stylus who still want to design neat drawings. These 
same people have very little space on their computers, but still like to keep art 
stored locally. They are either poor or cheapskates.

Introduce Froscii. With this, you can not only draw using text, which anyone 
can do, but it will also be converted into a finished product with seamless 
lines and style.


## 2. Top Questions to Resolve in Review

*List the most important questions you have about your design, or things that
you are still debating internally that you might like help working through.*

1.   Updating an image from a Lambda to HTML automatically
2.   Allowing the user to save that image
3.   A Javascript slideshow function.

## 3. Use Cases

*This is where we work backwards from the customer and define what our customers
would like to do (and why). You may also include use cases for yourselves, or
for the organization providing the product to customers.*

U1. As a Froscii customer, I want to see my converted image update when I change my
text art.

U2. As a Froscii customer, I want to see my image in a collection after I've added it
to that collection

U3. I want to be unable to add an image to a collection if 1, the collection is password-
protected, and 2, I did not enter the correct password.

U4. I want to see the latest-added image on the home page.

U5. I want to view an empty collection once it has been made.

U6. I want to be told that the name of a collection I'm making is taken if I am using a name that
is already in use.

U7. I want the most popular collections to be found at the top of the collections page.

## 4. Project Scope

*Clarify which parts of the problem you intend to solve. It helps reviewers know
what questions to ask to make sure you are solving for what you say and stops
discussions from getting sidetracked by aspects you do not intend to handle in
your design.*

### 4.1. In Scope

*Which parts of the problem defined in Sections 1 and 3 will you solve with this
design?*

* Continuous-lined, straight-edged characters to be converted
* Collection create button
* Collections page with alphabetized list
* Home page (which is just another collection)
* ASCII Art Converter page with "convert" and "post" buttons
* Collection page has "next", "previous", and "save" buttons
* ASCII Art, not image, is saved to database.
* Drawing posts have the drawing name and date posted.
* All Drawings are saved to the home collection.
* Width and height of ASCII art determined by user.

### 4.2. Out of Scope

*Based on your problem description in Sections 1 and 3, are there any aspects
you are not planning to solve? Do potential expansions or related problems occur
to you that you want to explicitly say you are not worrying about now? Feel free
to put anything here that you think your team can't accomplish in the unit, but
would love to do with more time.*

* Color for the Converted ASCII Art.
* A search engine for collections.
* Slopes in the CAA.
* Complete keyboard character implementation
* Information posted with drawings
* User data
* A downloadable conversion gif.
* An open door animation for unlocked collections.
* Passwords.
* Real-time image conversion updating
* A "font size" button on AAC page.
* Download image button (they could just screenshot)
* Lines are crisp (may happen automatically)
* converter version stored to Drawing
* time last viewed stored to Drawing
* saves stored to Drawing
* Drawings can be removed

# 5. Proposed Architecture Overview

*Describe broadly how you are proposing to solve for the requirements you
described in Section 3.*

*This may include class diagram(s) showing what components you are planning to
build.*

*You should argue why this architecture (organization of components) is
reasonable. That is, why it represents a good data flow and a good separation of
concerns. Where applicable, argue why this architecture satisfies the stated
requirements.*

# 6. API

## 6.1. Public Models

*Define the data models your service will expose in its responses via your
*`-Model`* package. These will be equivalent to the *`PlaylistModel`* and
*`SongModel`* from the Unit 3 project.*

## 6.2. *First Endpoint*

*Describe the behavior of the first endpoint you will build into your service
API. This should include what data it requires, what data it returns, and how it
will handle any known failure cases. You should also include a sequence diagram
showing how a user interaction goes from user to website to service to database,
and back. This first endpoint can serve as a template for subsequent endpoints.
(If there is a significant difference on a subsequent endpoint, review that with
your team before building it!)*

*(You should have a separate section for each of the endpoints you are expecting
to build...)*

## 6.3 *Second Endpoint*

*(repeat, but you can use shorthand here, indicating what is different, likely
primarily the data in/out and error conditions. If the sequence diagram is
nearly identical, you can say in a few words how it is the same/different from
the first endpoint)*

# 7. Tables

*Define the DynamoDB tables you will need for the data your service will use. It
may be helpful to first think of what objects your service will need, then
translate that to a table structure, like with the *`Playlist` POJO* versus the
`playlists` table in the Unit 3 project.*

Drawing:
* id: String, unique (KEY)
* name: String
* post_date: integer
* ascii_art: String, no newline escape codes
* art_width: integer

Collection:
* name: String, unique (KEY)
* Drawings: Stringlist, stores the IDs of Drawings
* *size stored implicity in Drawings*

# 8. Pages

*Include mock-ups of the web pages you expect to build. These can be as
sophisticated as mockups/wireframes using drawing software, or as simple as
hand-drawn pictures that represent the key customer-facing components of the
pages. It should be clear what the interactions will be on the page, especially
where customers enter and submit data. You may want to accompany the mockups
with some description of behaviors of the page (e.g. “When customer submits the
submit-dog-photo button, the customer is sent to the doggie detail page”)*
