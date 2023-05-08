const url = "https://kh6q7lldy4.execute-api.us-west-2.amazonaws.com/test/drawings";

// All wrapped after window loading so the HTML can create the form before it's queried.
window.onload = async function() {
    console.log("----Loading Post Page----");
    //create a reference to the form
    drawingForm = document.querySelector("#create-drawing-form");
    console.log(drawingForm);

    drawingForm.onsubmit = async function(evt) {
      console.log("----Beginning postDrawing function----")
      //pause the reload
      evt.preventDefault();
      evt.stopImmediatePropagation();
      //collect data from text entries
      let name = document.getElementById("name").value;
      let text = document.getElementById("textString").value;
      let headers = {
        'Content-Type': 'application/json'
      }
      console.log("----Creating Object----")
      //create an object from data
      let drawingObj = {
        "name": name,
        "text": text
      }

      //post object to DDB
      console.log("----Sending Object----")
      console.log(drawingObj)
      axios.post(url, drawingObj, headers)
        .then((response) => {
          console.log(response);
          console.log("-= HELP =-")
          window.location.reload();
        });
    }

    //TODO
    function convertDrawing() {
      //collect data from text entries

      //call non-existent convert lambda function

      //update photo with response
    }
}

