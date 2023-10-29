$(function () {
    $("#searchInputSoftware").autocomplete({
        source: function (request, response) {
            // Make an AJAX call to the server to fetch skill suggestions based on the user's input
            $.ajax({
                url: "/search-softwares",
                type: "GET",
                data: {
                    softwareName: request.term
                },
                success: function (data) {
                    response(data.map(function (item) {
                        return {
                            label: item.name,         // The skill name
                            value: item.name,         // The skill name
                            image: item.softwareImageUrl,     // The image URL
                            // Render the HTML for each search result item
                            html: "<div><img src='" + item.softwareImageUrl + "' alt='" + item.name + "'> " + item.name + "</div>"
                        };
                    }));
                }
            });
        },
        select: function (event, ui) {
            var selectedSoftware = ui.item.label;
            // Add the selected skill and its image to the list of selected skills
            $("#selectedSoftwares").append("<li><img src='" + ui.item.softwareImageUrl + "' alt='" + selectedSoftware + "'> " + selectedSoftware + " <button class='removeSoftware'>Remove</button></li>");
            $("#searchInputSoftware").val("");
            return false;
        }
    });

    $("#selectedSoftwares").on("click", "li .removeSoftware", function () {
        $(this).parent().remove();
    });

    $("#saveChangessoftware").click(function () {
        var selectedSoftwares = [];
        $("#selectedSoftwares li").each(function () {
            var softwareName = $(this).text().trim().split(' ')[0]; // Extract only the skill name
            selectedSoftwares.push(softwareName);
        });

        // Send an AJAX request to update the user's skills
        $.ajax({
            url: "/update-softwares",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(selectedSoftwares),
            success: function (data) {
                alert("Softwares updated successfully.");
                location.reload(); // Reload the page
            },
            error: function () {
                alert("Failed to update software.");
            }
        });
    });

    // Remove updated skills on click
    $("#updatedSoftwares").on("click", ".removeUpdatedSoftware", function () {
        var softwareName = $(this).text();
        var softwareToRemove = softwareName.trim();

        $.ajax({
            url: "/remove-updated-software", // Adjust the URL to match your endpoint
            type: "POST",
            data: {
                softwareName: softwareToRemove
            },
            success: function (data) {
                alert(data); // Show a success message
                $(this).parent().remove(); // Remove the skill tag from the UI
                location.reload(); // Reload the page
            },
            error: function () {
                alert("Failed to remove updated software.");
            }
        });
    });


});
