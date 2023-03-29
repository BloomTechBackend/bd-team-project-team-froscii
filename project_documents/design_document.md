# Team Froscii Design Document

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

U3. I want to be able to scroll through drawings in a collection.

U4. I want to see the latest-added image on the home page.

U5. I want to view an empty collection once it has been made.

U6. I want to be told that the name of a collection I'm making is taken if I am using a name that
is already in use.

U7. I want the most popular collections to be found at the top of the collections page.

## 4. Project Scope

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
* "Random Drawing" button.

# 5. Proposed Architecture Overview

The initial release version of the Froscii website will have 3 main pages, with drawing and collection
creation and viewing. For now, all drawings will be made of straight lines.

We will use AWS Lambda to convert images on demand. We will pair it with API Gateway
for the functions `CreateCollection`, `AddToCollection`, `ConvertText`, and
`ViewImageFromCollection`. Given that the home page is no different from any other
collection, the AddToCollection function will be called when any drawing is created, nullifying
the need for a 5th function, `CreateImage`. ViewImageFromCollection may not be necessary, given that we
could keep track of which image we are viewing through the html request and ConvertText alone. ConvertText
only needs a drawingID anyhow. If it is needed, it will call the ConvertText funciton,
and will also keep track of what page the user is on to determine which image to display.
No object deletion will be implemented, as we want as much content produced on the get-go as we can get.

We believe these plans will satisfy the customer. This is a somewhat novel idea, afterall.

# 6. API

## 6.1. Public Models
```
// DrawingModel
String id;
String name;
String textArt;
int width;
```
```
// CollectionModel
String name;
List<String> drawings;
```
## 6.2. *GET Collection Endpoint*

* Accepts `GET` requests to `/collections/:name`
* Accepts the name of a collection and returns the corresponding collection.
    * If the given collection name is not found, it will throw a
      `CollectionNotFoundException`

## 6.3. *GET Drawing Endpoint*

* Accepts `GET` requests to `/collections/:name/`
* Accepts the ID of a drawing and returns the corresponding drawing.
    * If the given drawing ID is not found, it will throw a
      `DrawingNotFoundException`
    * If the given drawing breaks while rendering, it will throw a
      `CorruptDrawingException`

## 6.4. *POST Collection Endpoint*

* Accepts `POST` requests to `/collections`
* Accepts the name of a collection and creates the corresponding collection.
    * If the given collection name is not allowed, will throw a
      `IllegalCollectionNameException`

## 6.5. *PUT Collection Endpoint*

* Accepts `PUT` requests to `/collections/:name`
* Accepts the name of a Drawing and its text form, and the name of the collection if
  it's not being added to the home page, and updates the corresponding collection
  with a new drawing.
    * If the given collection name is not found, will throw a
      `CollectionNotFoundException`
    * If the given drawing id is not found, will throw a
      `DrawingNotFoundException`

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
* *size stored implicitly in Drawings*

# 8. Pages

## 8.1 Home Page
![The Home Page, which is just another collection, which displays
just one image at a time.](../frontend/HTML%20mocks/home%20page%20html%20mock.png)

## 8.2 Post Page
![The Post Page, where you can edit drawings and name them before
posting.](../frontend/HTML%20mocks/post%20page%20html%20mock.png)

## 8.3 Collections Page
![The Collections Page, where you can create a collection or view
the names of other collections](../frontend/HTML%20mocks/collections%20page%20html%20mock.png)

## 8.4 Specific Collection Page
![The Page for a particular collection. Just like the Home Page,
you may view one image at a time.](../frontend/HTML%20mocks/specific%20collection%20page%20html%20mock.png)