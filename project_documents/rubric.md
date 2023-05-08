# Froscii Project Rubric

## Background

*This captures the expectations that we have for your team during the unit.
This is how we will evaluate whether you have demonstrated these expectations.*

## Instructions

*As a team, complete this rubric (everything except for the Appendix) by
answering the questions below. Each question should usually only require one or
two sentences, so please be brief. The remainder of expectations will be
evaluated by instructors, so you don’t need to write anything in the Appendix.
We want you to see the full set of expectations for transparency’s sake.*

## Deliverables

*Provide links to the following project deliverables:*

|Deliverable                                                      |Due Date                  |Date Completed |URL                               |
|---                                                              |---                       |---            |---                               |
|Team name                                                        |Sprint 1 Module 1         |3/2/23        |name: Froscii                     |
|[Design Document - problem statement](design_document.md)        |Sprint 1 Module 2         |4/20/23       |                                  |
|[Team Charter](team_charter.md)                                  |Sprint 1 Module 3         |4/20/23       |                                  |
|[Design Document](design_document.md)                            |Sprint 1 REQUIRED TO GO ON|4/20/23       |                                  |
|Project Completion (Feature Complete)                            |Sprint 3                  |5/8/23        |https://froscii-website.s3.us-west-2.amazonaws.com/post.html                                  |
|[Team Reflection](reflection.md)                                 |Sprint 3                  |5/8/23        |                                  |
|[Accomplishment Tracking (person 1)](accomplishment_tracking.md) |Sprint 3                  |5/8/23        |                                  |
|Self Reflection                                                  |Sprint 3                  |5/8/23        |n/a (will be submitted via Canvas - "Wrap-up" section) |

## Technical Learning Objectives

### API Design

**Design an API to meet the needs of your application.** Provide a link to the
definition for your endpoints (can be code/configuration, or can be
documentation). List one thing that your team learned about designing a good
API.

*Endpoint definition location:*
https://froscii-website.s3.us-west-2.amazonaws.com/post.html
*What we learned:*    
This was much less important than it sounded. It's good to use shorter words,
and it should be easy to understand.

**Develop a service endpoint definition that uses complex inputs and outputs.**
Select one of your endpoints and list the operation’s input and output objects.
Under each, list its attributes.

*Endpoint:*     
/drawings
*Input object(s):*      

The following is JSON data:
* name
This is the name of a drawing. It must be unique
* text
This is the ASCII art of a drawing.

*Output object(s):*      

* JSON Response
hopefully a 200 haha.

**Given a user story that requires a user to provide values to a service
endpoint, design a service endpoint including inputs, outputs, and errors.**
Select one of your endpoints that accepts input values from a client. List the
error cases you identified and how the service responds in each case. Provide at
most 3 error cases.

| **Endpoint:**    |                              |
|------------------|------------------------------|
| **Error case**   | **Service response**         |
| Name that exists | Error 400: NameAlreadyExists |
| Website Down     | Error 500                    |
|                  |                              |

**Determine whether a given error condition should result in a client or server
exception.** List one client exception and one server exception that your
service code throws. List one condition in which this exception is thrown.

|                       | **Exception**                | **One case in which it is thrown** |
|---	                |------------------------------|------------------------------------|
|**Client exception:**  | CollectionNameAlreadyExists	 | 	creating a 'home' Collection      |
|**Service exception:** | Internal Server Error	       | Website is deleted or down	        |

### DynamoDB Table Design

**Decompose a given set of use cases into a set of DynamoDB tables that provides
efficient data access.** List the DynamoDB tables in your project:

1.  collections
2.  drawings


**Design a DynamoDB table key schema that allows items to be uniquely
identified.** Describe the primary key structure for your DynamoDB table with
the most interesting primary key. In a sentence or two, explain your choice of
partition/sort key(s).

1. Collections are sorted by the primary key "name". All names must be unique, or an error is thrown

**Design the attributes of a DynamoDB table given a set of use cases.** List a
DynamoDB table with at least 2 attributes. List one relevant use case that uses
the attribute. In one sentence, describe why the attribute is included.

**Table name:**   
 Collections
**Attributes:**

| Attribute name | (One) relevant use case                           | attribute purpose          |
|----------------|---------------------------------------------------|----------------------------|
| Name           | Look inside your favorite collection of drawings  | make drawings easy to save |
| drawingNames   | Know what new items are stored in this collection | store names of drawings    |
|                |                                                   |                            |
|                |                                                   |                            |
|                |                                                   |                            |

### DynamoDB Indexes

**Design a GSI key schema and attribute projection that optimizes queries not
supported by a provided DynamoDB table.** In one or two sentences, explain why
you created one of the GSIs that your project uses, including one use case that
uses that index.

**Table name:**
Drawings
**Table keys:**
Name
**GSI keys:**
dateCreated
**Use case for GSI:**
Look at the most recent drawings in a collection
NOT IMPLEMENTED
**Implement functionality that uses query() to retrieve items from a provided
DynamoDB's GSI.** List one of your use cases that uses `query()` on a GSI.

**Table name:**
collections
**Use case for `query()` on GDI:**
get every collection to view on the Collections page.
DID NOT WORK, PROFESSORS DID NOT KNOW HOW TO HELP
## Soft(er) Outcomes

**Learn a new technology.** For each team member, list something new that that
team member learned:

| Team Member | Something new the team member learned |   
|-------------|---------------------------------------|
| David       | Being alone is how I fail             |   
| ...         | ...                                   |     
| ...         | ...                                   |     
|             |                                       |     

**Identify key words to research to accomplish a technical goal | Use sources
like sage and stack overflow to solve issues encountered while programming.**
Give an example of a search term that your team might have used to find an
answer to a technical question/obstacle that your team ran into. List the
resource that you found that was helpful.

**Search terms:**      
AWS, copy-and-paste-an-error, Lambda, etc
**Helpful resource:**      
stackoverflow, chat.openai, googling base terms.

**Find material online to learn new technical topics.** List one resource that
your team found on your own that helped you on your project.

**Topic:**
I wanted to create button elements that worked immediately to copy text that you can't necessarily see
automatically. This helped in telling me something that DIDn't work. 
**Resource:**
https://stackoverflow.com/questions/65473187/how-to-create-copy-button-using-html-and-javascript

**Share what was worked on yesterday, the plan for today, and any blockers as a
part of a scrum standup.** In one or two sentences, describe what your team
found to be the most useful outcome from holding daily standups.

Yesterday, I planned out the rest of the work today, which was really just wrapping up the project. I was
sure that I wouldn't solve the problem of empty-scanning for my whole table of collection items, and I was write.
The next blocker is whether you accept this project.

The most useful outcome from any of my lonely scrum meetings was again discovering what to leave behind. Only then
could I move on to finish this within the month.

