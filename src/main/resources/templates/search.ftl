<#include "base.ftl">
<@base title="Search"/>
<#macro bodyPage>
    <div class="navbar-fixed">
        <nav>
            <div class="nav-wrapper indigo darken-2">
                <form>
                    <div class="input-field">
                        <input id="search" type="search" required>
                        <label class="label-icon" for="search"><i class="material-icons">search</i></label>
                        <i class="material-icons">tune</i>
                    </div>
                </form>
            </div>
        </nav>
    </div>
    <!-- Modal Structure -->
    <div id="modal1" class="modal">
        <div class="modal-content">
            <h4>Search settings</h4>

            <div class="input-field col s12">
                <select>
                    <option value="" disabled selected>Choose your category</option>
                    <option value="1">Option 1</option>
                    <option value="2">Option 2</option>
                    <option value="3">Option 3</option>
                </select>
                <label>Category</label>
            </div>
            <div class="input-field col s12">
                <select>
                    <option value="" disabled selected>Choose your category</option>
                    <option value="1">Option 1</option>
                    <option value="2">Option 2</option>
                    <option value="3">Option 3</option>
                </select>
                <label>Category</label>
            </div>
            <div id="test-slider"></div>
        </div>
        <div class="modal-footer">
            <a href="#!" class="modal-close waves-effect waves-green btn-flat">Agree</a>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <#list libraries as library>
                <div class="col s12">
                    <div class="card ">
                        <div class="card-stacked">
                            <div class="card-content">
                        <span class="card-title">
                            <img width="40" height="40"
                                 src="https://d2j3q9yua85jt3.cloudfront.net/img/ff6fd9c8fca99fb1041c202f3147d577" alt=""
                                 class="circle">
                            <a href="/library/${library.id}"><span
                                        class="my-card-title ">${library.artifactId}</span></a>
                            <span class="right usage">${library.usage} usage</span>
                        </span>
                                <p>${library.description}</p>
                            </div>
                            <div class="card-action">
                                <#list library.blogs as blog>
                                    <div class="chip">
                                        <img src="${blog.img}" alt="Contact Person">
                                        ${blog.name}
                                    </div>
                                </#list>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>

</#macro>