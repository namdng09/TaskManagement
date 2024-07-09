<%-- 
    Document   : workspace-board
    Created on : Jul 6, 2024, 9:24:11 AM
    Author     : namdng09
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Task Tracking</title>
        <link href="css/boardstyle.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
        <!-- Include TinyMCE -->
        <script src="https://cdn.tiny.cloud/1/tjrh9b5oertj1hnxpvoigqbbi4l9n3c27gmjtxekwbxdwuqp/tinymce/7/tinymce.min.js" referrerpolicy="origin"></script>
        <script>
            tinymce.init({
                selector: '#mytextarea'
            });
        </script>

    </head>
    <body>

        <c:set var="board" value="${requestScope.board}"/>
        <c:set var="user" value="${sessionScope.account}"/>
        <div class="board" style="background-image: url('${board.image}');">
            <div class="board-header">
                <div class="container">
                    <!-- Left Side Content -->
                    <div class="left-side">
                        <!-- Board Name -->
                        <h1 id="boardName" style="cursor: pointer;">${board.boardName}</h1>
                        <form id="renameBoardForm" action="board" method="post" style="display: none;">
                            <input type="hidden" id="boardID" name="boardID" value="${board.boardID}">
                            <input type="hidden" id="action" name="action" value="rename">
                            <input type="text" id="newName" name="name" value="${board.boardName}" required>
                            <!-- Dummy button to enable Enter key submission -->
                            <button type="submit" style="display: none;"></button>
                        </form>
                        <div class="clearfix"></div>
                    </div>
                    <div class="right-side">
                        <style>
                            /* General button styles */
                            .button {
                                display: inline-block;
                                padding: 10px 20px;
                                font-size: 16px;
                                font-weight: bold;
                                text-align: center;
                                text-decoration: none;
                                border-radius: 4px;
                                transition: background-color 0.3s ease, box-shadow 0.3s ease;
                            }

                            /* Red button styles */
                            .button.red {
                                background-color: #e74c3c; /* Red background color */
                                color: #ffffff; /* White text color */
                                border: none;
                            }

                            .button.red:hover {
                                background-color: #c0392b; /* Darker red on hover */
                                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Shadow on hover */
                            }

                            /* Ripple effect styles */
                            .button.ripple-effect {
                                position: relative;
                                overflow: hidden;
                            }

                            .button.ripple-effect::after {
                                content: '';
                                position: absolute;
                                top: 50%;
                                left: 50%;
                                width: 20px;
                                height: 20px;
                                background: rgba(255, 255, 255, 0.5);
                                border-radius: 50%;
                                transform: scale(1);
                                opacity: 0;
                                pointer-events: none;
                                transition: width 0.3s ease, height 0.3s ease, opacity 0.3s ease;
                            }

                            .button.ripple-effect:active::after {
                                width: 200px;
                                height: 200px;
                                opacity: 0;
                                transform: translate(-50%, -50%) scale(0);
                            }

                            /* Icon styles */
                            .ico {
                                display: inline-block;
                                vertical-align: middle;
                                margin-right: 8px;
                            }

                            /* Remove button styles */
                            .remove-button {
                                cursor: pointer;
                            }

                            /* Tooltip styles */
                            [data-tippy-placement] {
                                position: relative;
                            }

                            [data-tippy-placement]::before {
                                content: attr(title);
                                position: absolute;
                                background: rgba(0, 0, 0, 0.8);
                                color: #fff;
                                padding: 4px 8px;
                                border-radius: 4px;
                                white-space: nowrap;
                                opacity: 0;
                                transition: opacity 0.3s ease;
                                pointer-events: none;
                                transform: translateY(-10px);
                            }

                            [data-tippy-placement]:hover::before {
                                opacity: 1;
                                transform: translateY(0);
                            }

                        </style>
                        <div class="sidebar-search-button-container">
                            <a class="button ripple-effect" href="home"><i class="fa-solid fa-house"></i> Home</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="lanes">
                <c:forEach items="${board.listOfTask}" var="listTask" varStatus="loop">
                    <div class="list-task">
                        <div class="list-task-header">
                            <h3 id="listTaskTitle_${loop.index}" style="cursor: pointer;">${listTask.listTaskName}</h3>
                            <form id="renameListTaskForm_${loop.index}" action="list" method="post" style="display: none;">
                                <input type="hidden" id="boardID" name="boardID" value="${board.boardID}">
                                <input type="hidden" id="listTaskID_${loop.index}" name="listTaskID" value="${listTask.listTaskID}">
                                <input type="hidden" id="action_${loop.index}" name="action" value="renameListTask">
                                <input type="text" id="newName_${loop.index}" name="name" value="${listTask.listTaskName}" required>
                                <!-- Dummy button to enable Enter key submission -->
                                <button type="submit" style="display: none;"></button>
                            </form>
                            <script type="text/javascript">
                                // Event listener to show edit form when clicking list task title
                                document.getElementById('listTaskTitle_${loop.index}').addEventListener('click', function () {
                                    document.getElementById('listTaskTitle_${loop.index}').style.display = 'none';
                                    document.getElementById('renameListTaskForm_${loop.index}').style.display = 'block';
                                    document.getElementById('newName_${loop.index}').focus();
                                });

                                // Event listener to cancel edit form
                                document.addEventListener('click', function (event) {
                                    var isClickInside = document.getElementById('renameListTaskForm_${loop.index}').contains(event.target);
                                    var isClickListTaskTitle = (event.target === document.getElementById('listTaskTitle_${loop.index}'));
                                    if (!isClickInside && !isClickListTaskTitle) {
                                        cancelEdit_${loop.index}();
                                    }
                                });

                                // Function to cancel edit form
                                function cancelEdit_${loop.index}() {
                                    document.getElementById('renameListTaskForm_${loop.index}').style.display = 'none';
                                    document.getElementById('listTaskTitle_${loop.index}').style.display = 'block';
                                }
                            </script>

                            <a href="#" onclick="confirmDelete('${listTask.listTaskID}', '${board.boardID}');
                                    return false;" class="btn btn-danger delete-list-task-button">
                                <i class="fa fa-trash"></i> <!-- Or use <i class="fas fa-trash"></i> -->
                            </a>
                        </div>
                        <c:forEach items="${listTask.cards}" var="card">
                            <div class="card-class" style="cursor: pointer;" data-bs-toggle="modal" data-bs-target="#cardModal_${card.cardID}">
                                <div class="card-name">${card.cardName}</div>
                            </div>

                            <!-- Modal for each card -->
                            <div class="modal fade" id="cardModal_${card.cardID}" tabindex="-1" aria-labelledby="cardModalLabel_${card.cardID}" aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="cardModalLabel_${card.cardID}">${card.cardName}</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="container-fluid">
                                                <div class="row">
                                                    <div class="col-md-9">
                                                        <div class="date-class" style="padding: 10px;">
                                                            <h4><i class="fa-regular fa-clock"></i> Date</h4>
                                                            <span id="formattedDueDate_${card.cardID}"></span>
                                                            <span id="dueStatus_${card.cardID}" class="status"></span>
                                                        </div>

                                                        <script type="text/javascript">
                                                            document.addEventListener("DOMContentLoaded", function () {
                                                                var formattedDueDate = document.getElementById("formattedDueDate_${card.cardID}");
                                                                var dueStatus = document.getElementById("dueStatus_${card.cardID}");

                                                                // Format and display due date
                                                                var dueDate = new Date("${card.dueDate}"); // Replace "${card.dueDate}" with actual date string
                                                                var formattedDate = formatDate(dueDate, "yyyy-MM-dd HH:mm");
                                                                formattedDueDate.textContent = formattedDate;

                                                                // Determine status
                                                                var now = new Date();
                                                                if (dueDate < now) {
                                                                    dueStatus.textContent = "(overdue)";
                                                                    dueStatus.classList.add("overdue");
                                                                } else {
                                                                    dueStatus.textContent = "(completed)";
                                                                    dueStatus.classList.add("completed");
                                                                }
                                                            });

                                                            function formatDate(date, format) {
                                                                var formatter = new Intl.DateTimeFormat('en', {
                                                                    year: 'numeric',
                                                                    month: '2-digit',
                                                                    day: '2-digit',
                                                                    hour: '2-digit',
                                                                    minute: '2-digit',
                                                                    hour12: false
                                                                });
                                                                var parts = formatter.formatToParts(date);
                                                                var formatted = '';
                                                                for (var part of parts) {
                                                                    switch (part.type) {
                                                                        case 'year':
                                                                            formatted += part.value + ' ';
                                                                            break;
                                                                        case 'month':
                                                                            formatted += part.value + '-';
                                                                            break;
                                                                        case 'day':
                                                                            formatted += part.value + '-';
                                                                            break;
                                                                        case 'hour':
                                                                            formatted += part.value + ':';
                                                                            break;
                                                                        case 'minute':
                                                                            formatted += part.value;
                                                                            break;
                                                                    }
                                                                }
                                                                return formatted;
                                                            }
                                                        </script>

                                                        <style>
                                                            .overdue {
                                                                color: red;
                                                            }
                                                            .completed {
                                                                color: green;
                                                            }
                                                        </style>
                                                        <div class="description-title" onclick="toggleForm('myForm_${card.cardID}')">
                                                            <h4><i class="fa-solid fa-bars"></i> Description</h4>
                                                        </div>
                                                        <div id="description_${card.cardID}" class="card-description">
                                                            ${card.description}
                                                        </div>

                                                        <form id="myForm_${card.cardID}" action="card" method="post" class="hidden">
                                                            <textarea name="textarea" id="default_${card.cardID}" cols="15" rows="10" ></textarea>
                                                            <input type="hidden" name="boardID" value="${board.boardID}">
                                                            <input type="hidden" name="cardID" value="${card.cardID}">
                                                            <input type="hidden" name="action" value="changeDescription">
                                                            <button type="submit">Submit</button>
                                                        </form>

                                                        <script type="text/javascript">
                                                            function toggleForm(formId) {
                                                                var form = document.getElementById(formId);
                                                                if (form.classList.contains("hidden")) {
                                                                    form.classList.remove("hidden");
                                                                } else {
                                                                    form.classList.add("hidden");
                                                                }
                                                            }
                                                        </script>

                                                        <div class="Check-List-class" style="padding: 10px;">
                                                            <h4><i class="fa-solid fa-list-check"></i> Check List</h4>
                                                        </div>

                                                        <!-- Loop through each checklist and create a form for each item -->
                                                        <c:forEach items="${card.checkLists}" var="checklist">
                                                            <form action="card" method="post" style="display: flex; align-items: center; width: 100%;" >
                                                                <input type="checkbox" id="checkbox_${checklist.checkListID}" ${checklist.checked ? 'checked' : ''} 
                                                                       onchange="this.form.submit()" name="checkListStatus" value="completed">
                                                                <span style="margin-left: 10px; flex-grow: 1;">${checklist.checkListTitle}</span>
                                                                <input type="hidden" name="boardID" value="${board.boardID}">
                                                                <input type="hidden" name="cardID" value="${card.cardID}">
                                                                <input type="hidden" name="checkListID" value="${checklist.checkListID}">
                                                                <input type="hidden" name="action" value="updateCheckListStatus">
                                                                <a href="card?boardID=${board.boardID}&cardID=${card.cardID}&checkListID=${checklist.checkListID}&action=removeCheckList" 
                                                                   style="margin-left: 10px;">
                                                                    <i class="fa-solid fa-trash" style="color: red;"></i>
                                                                </a>
                                                            </form>
                                                        </c:forEach>

                                                        <div class="Comment-class" onclick="toggleForm('myForm2_${card.cardID}')">
                                                            <h4><i class="fa-regular fa-comment"></i> Comment</h4>
                                                        </div>

                                                        <form id="myForm2_${card.cardID}" action="card" method="post" class="hidden">
                                                            <textarea name="textarea" id="default_${card.cardID}"rows="3" ></textarea>
                                                            <input type="hidden" name="boardID" value="${board.boardID}">
                                                            <input type="hidden" name="user_uid" value="${user.userUID}">
                                                            <input type="hidden" name="cardID" value="${card.cardID}">
                                                            <input type="hidden" name="action" value="addComment">
                                                            <button type="submit">Submit</button>
                                                        </form>

                                                        <!-- Debug output to check comment properties -->
                                                        <c:forEach items="${card.comments}" var="cmt">
                                                            <style>
                                                                .card-comment p {
                                                                    margin-bottom: 0 !important;
                                                                }
                                                            </style>
                                                            <div id="comment_${cmt.commentID}" class="card-comment" style="display:flex; align-items:center;">
                                                                <span style="margin-right: 10px;">${cmt.firstName} ${cmt.lastName}:</span>
                                                                <span style="margin-right: 10px;">${cmt.comment}</span>
                                                                <span style="margin-right: 10px;">(<fmt:formatDate value="${cmt.createDate}" pattern="HH:mm"/>)</span>
                                                            </div>
                                                            <a href="card?boardID=${board.boardID}&cardID=${card.cardID}&commentID=${cmt.commentID}&action=removeComment">remove</a>
                                                        </c:forEach>


                                                    </div>
                                                    <div class="col-md-3">
                                                        <div class="card-tool">
                                                            <form action="card" method="post">
                                                                <div class="mb-3">
                                                                    <input type="hidden" name="boardID" value="${board.boardID}">
                                                                    <input type="hidden" name="cardID" value="${card.cardID}">
                                                                    <input type="hidden" name="action" value="addCheckList">
                                                                    <input type="text" class="form-control" name="checkListName" placeholder="Enter Check List">
                                                                </div>
                                                                <button type="submit" class="btn btn-primary">Add Check List</button>
                                                            </form>

                                                        </div>

                                                        <!-- Đoạn HTML để nhấp vào để bật input datetime-local -->
                                                        <div id="trigger_${card.cardID}" style="cursor: pointer;" onclick="showDateTimeInput('dateTimeInput_${card.cardID}')">
                                                            Set date and time
                                                        </div>

                                                        <!-- Form chứa input date-time -->
                                                        <form id="dateForm_${card.cardID}" action="card" method="post">
                                                            <input type="hidden" name="boardID" value="${board.boardID}">
                                                            <input type="hidden" name="cardID" value="${card.cardID}">
                                                            <input type="hidden" name="action" value="setDueDate">
                                                            <input type="datetime-local" id="dateTimeInput_${card.cardID}" name="dateTime" class="hidden">
                                                            <button type="submit" class="hidden" id="submitBtn_${card.cardID}">Submit</button>
                                                        </form>

                                                        <script type="text/javascript">
                                                            function showDateTimeInput(inputId) {
                                                                var dateTimeInput = document.getElementById(inputId);
                                                                dateTimeInput.classList.remove('hidden');
                                                                dateTimeInput.focus();

                                                                // Khi người dùng chọn ngày và giờ, tự động submit form
                                                                dateTimeInput.addEventListener('change', function () {
                                                                    var dateForm = dateTimeInput.closest('form');
                                                                    if (dateTimeInput.value) {
                                                                        dateForm.submit();
                                                                    } else {
                                                                        alert('Please select a valid date and time.');
                                                                    }
                                                                });
                                                            }
                                                        </script>

                                                        <div class="card-tool">
                                                            <form id="moveCardForm_${card.cardID}" action="card" method="post">
                                                                <input type="hidden" name="boardID" value="${board.boardID}">
                                                                <input type="hidden" name="cardID" value="${card.cardID}">
                                                                <input type="hidden" name="action" value="moveCard">
                                                                <label for="listTaskSelect_${card.cardID}">Move to:</label>
                                                                <select id="listTaskSelect_${card.cardID}" name="listTaskID" onchange="submitMoveCardForm('${card.cardID}')">
                                                                    <c:forEach items="${board.listOfTask}" var="targetList">
                                                                        <option value="${targetList.listTaskID}" ${targetList.listTaskID == listTask.listTaskID ? 'selected' : ''}>
                                                                            ${targetList.listTaskName}
                                                                        </option>
                                                                    </c:forEach>
                                                                </select>
                                                            </form>
                                                        </div>
                                                        <div class="card-tool">
                                                            <a href="card?cardID=${card.cardID}&action=removeCard&boardID=${board.boardID}">Remove</a>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                        <button type="button" class="btn btn-primary add-card-button"
                                data-bs-toggle="modal"
                                data-bs-target="#addListModal_${loop.index}">+ Add new card</button>

                        <!-- The Modal -->
                        <div class="modal fade" id="addListModal_${loop.index}" tabindex="-1" aria-labelledby="addListModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog modal-dialog-custom">
                                <div class="modal-content">
                                    <div class="modal-body">
                                        <form action="list" method="post">
                                            <div class="mb-3">
                                                <input type="hidden" name="listTaskID" value="${listTask.listTaskID}">
                                                <input type="hidden" name="boardID" value="${board.boardID}">
                                                <input type="hidden" name="action" value="addNewCard">
                                                <input type="text" class="form-control" name="cardName"
                                                       placeholder="Enter Card Name">
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <button type="submit" class="btn btn-primary">Add Card</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <!-- Add New ListTask Form -->
                <form id="todo-form" method="POST" action="board">
                    <input type="hidden" name="boardID" value="${board.boardID}">
                    <input type="hidden" name="action" value="addNewListTask">
                    <input type="text" name="name" placeholder="Enter ListTask name">
                    <button type="submit">Add +</button>
                </form>
            </div>

        </div>
        <script type="text/javascript">
            function confirmDelete(listTaskID, boardID) {
                console.log("confirmDelete function called"); // Debug log
                if (confirm('Are you sure you want to delete this task list?')) {
                    console.log("User confirmed deletion"); // Debug log
                    window.location = "list?listTaskID=" + encodeURIComponent(listTaskID) + "&boardID=" + encodeURIComponent(boardID) + "&action=removeListTask";
                } else {
                    console.log("User canceled deletion"); // Debug log
                }
            }
        </script>

        <script type="text/javascript">
            // Event listener to show edit form when clicking board name
            document.getElementById('boardName').addEventListener('click', function () {
                document.getElementById('boardName').style.display = 'none';
                document.getElementById('renameBoardForm').style.display = 'block';
                document.getElementById('newName').focus();
            });

            // Event listener to cancel edit form
            document.addEventListener('click', function (event) {
                var isClickInside = document.getElementById('renameBoardForm').contains(event.target);
                var isClickBoardName = (event.target === document.getElementById('boardName'));
                if (!isClickInside && !isClickBoardName) {
                    cancelEdit();
                }
            });

            // Function to cancel edit form
            function cancelEdit() {
                document.getElementById('renameBoardForm').style.display = 'none';
                document.getElementById('boardName').style.display = 'block';
            }

        </script>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">

        <!-- Your existing HTML content -->

        <!-- Bootstrap JS Bundle (includes Popper.js) -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>

        <!-- JavaScript to Open Modal Based on URL -->
        <script>
            // Function to open modal based on URL hash fragment
            function openModalFromUrl() {
                var url = new URL(window.location.href);
                var modalId = url.hash.substring(1); // Extract modal ID from URL hash

                if (modalId) {
                    var modal = document.getElementById(modalId);
                    if (modal) {
                        var bsModal = new bootstrap.Modal(modal); // Create Bootstrap Modal instance
                        bsModal.show(); // Show the modal
                    }
                }
            }

            // Call the function when the document is ready
            document.addEventListener('DOMContentLoaded', function () {
                openModalFromUrl(); // Open modal on page load if there's a hash in the URL
            });
        </script>
        <!-- Place the following <script> and <textarea> tags your HTML's <body> -->
        <script>
            tinymce.init({
                selector: 'textarea',
                plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist mediaembed casechange export formatpainter pageembed linkchecker a11ychecker tinymcespellchecker permanentpen powerpaste advtable advcode editimage advtemplate ai mentions tinycomments tableofcontents footnotes mergetags autocorrect typography inlinecss markdown',
                toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | addcomment showcomments | spellcheckdialog a11ycheck typography | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
                tinycomments_mode: 'embedded',
                tinycomments_author: 'Author name',
                mergetags_list: [
                    {value: 'First.Name', title: 'First Name'},
                    {value: 'Email', title: 'Email'},
                ],
                ai_request: (request, respondWith) => respondWith.string(() => Promise.reject("See docs to implement AI Assistant")),
            });
        </script>
        <script type="text/javascript">
            function toggleForm(formId) {
                var form = document.getElementById(formId);
                if (form.classList.contains("hidden")) {
                    form.classList.remove("hidden");
                } else {
                    form.classList.add("hidden");
                }
            }
        </script>
        <script type="text/javascript">
            function submitMoveCardForm(cardID) {
                document.getElementById('moveCardForm_' + cardID).submit();
            }
        </script>


    </body>

</html>

