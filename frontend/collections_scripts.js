const baseURL = "https://kh6q7lldy4.execute-api.us-west-2.amazonaws.com/test/";
const url = baseURL + "collections";
const allUrl = baseURL + "allcollections";
const getCollectionUrl = baseURL + "getcollection";
const getDrawingUrl = baseURL + "getdrawing";

const headers = {
  'Content-Type': 'application/json'//,
  //'Access-Control-Allow-Origin': '*'
}

// All wrapped after window loading so the HTML can create the form before it's queried.
window.onload = async function() {
    console.log("----Loading Collection Page----");
    //create a reference to the form
    collectionForm = document.querySelector("#create-collection-form");
    console.log(collectionForm);

    collectionForm.onsubmit = async function(evt) {
      console.log("----Beginning postCollection function----")
      //pause the reload
      evt.preventDefault();
      evt.stopImmediatePropagation();
      //collect data from text entries
      let name = document.getElementById("name").value;
      console.log("----Creating Object----")
      //create an object from data
      let collectionObj = {
        "name": name
      }

      //post object to DDB
      console.log("----Sending Object----")
      console.log(collectionObj)
      axios.put(url, collectionObj, headers)
        .then((response) => {
          console.log(response);
          console.log("----Object Sent----")
          window.location.reload();
        });
    }

    //Make all items in section "list" into buttons
    let ourList = document.getElementById("collection-list");
    ourList.addEventListener("click", activateItem);
    function activateItem(evt) {
        if (evt.target.nodeName == "LI" && !evt.target.innerHTML.includes("<")) {
            let collectionName = evt.target.innerHTML;
            let collectionObj = {
                "name": collectionName
            }
            console.log("----Sending Request for " + collectionName + " collection----")
            axios.post(getCollectionUrl, collectionObj, headers)
                .then((response) => {
                    console.log(response)
                    //Put all returned items into a list within this list item
                    let drawingNames = response.data.collection.drawingNames;
                    for (i = 0; i < drawingNames.length; i++) {
                        let newListItem = document.createElement("button")
                        newListItem.onclick = copyDrawingText;
                        newListItem.innerHTML = drawingNames[i];
                        console.log(drawingNames[i])
                        evt.target.append(newListItem)

                    }
                })
        }
    }
    function copyDrawingText(evt) {
        drawingObj = {
            "name": evt.target.innerHTML
        }
        axios.post(getDrawingUrl, drawingObj, headers)
            .then((response) => {
                console.log(response)
                let textToCopy = response.data.drawing.text
                // Put text into ASCII art text box
                document.getElementById("textGoHere").innerHTML = textToCopy
                // Copy the text of the drawing
                // Create a ghost element to copy from
                const input = document.createElement('textarea');
                input.value = textToCopy;
                document.body.appendChild(input);
                input.select();
                document.execCommand('copy');
                // Remove the used ghost element
                document.body.removeChild(input);
                console.log("----Text successfully copied----")
                console.log(textToCopy + "\n----End of copied Text----")
            })

    }

//    let emptyObj = {}
//    //automatically make a DDB call for a list of collections
//    axios.post(allUrl, emptyObj, headers)
//      .then((response) => {
//        console.log(response);
//        console.log("----List of Collections hopefully received----");
//        //make all items in the list into buttons
//        for (i = 0; i < response.size();i++) {
//            createElement("LI");
//        }
//        //when a collection name is clicked, its items are revealed underneath
//        //make sure it cannot be clicked again
//        //populate the collectionList element with those returned items
//
//      })
}

