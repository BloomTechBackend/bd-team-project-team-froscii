# Learn and Be Curious Project Files
This project was created as a freely-chosen solo assignment from the Bloomtech Institute of Technology.
# How to use:
To create your own drawing, simply load GraphicsApp.java from within the src folder.
It will prompt your for text art that the drawing will be based on. Make sure that the longest line is the first one. All other lines may be as short as one character.
The input for text art will complete when the program is given a line without a character in it.
Then it will ask for a name. The drawing will be saved in the prints folder, and will overwrite a drawing of the same name.
# Additional Information
Example art pieces, many modified with MS Paint, are found in the *prints* folder, as well as some reference text art that some of them came from.

All class files within *src* besides those found in the *Parts* and *dynamodb* folders act as the code for 6 AWS Lambda Functions which are used by the website we built to save drawings made with text art. The website frontend material is in the *frontend* folder. As of 5/9/2023, the website was located here:

https://froscii-website.s3.us-west-2.amazonaws.com/post.html

As of the same date, all it does is save the text art itself, and not actual images.
